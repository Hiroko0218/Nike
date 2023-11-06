# 自定義分頁結果類型

使用PageHelper實現分頁時，結果通常使用`PageInfo`類型，但是，此類型中包含的相關數據太多，可能是沒有必要的，例如：

```
prePage=0, 
nextPage=2, 
isFirstPage=true, 
isLastPage=false, 
hasPreviousPage=false, 
hasNextPage=true, 
navigatePages=8, 
navigateFirstPage=1, 
navigateLastPage=4, 
navigatepageNums=[1, 2, 3, 4]
```

以上數據基本上都是不必要的！

為了使得響應到客戶端的數據是簡單、有效的，可以自行定義數據類型，作為分頁的數據結果，例如：

```java
@Data
@Accessors(chain = true)
public class PageData<T> implements Serializable {

    private Integer pageNum;
    private Integer pageSize;
    private Long total;
    private Integer maxPage;
    private List<T> list;

}
```

由於將`PageInfo`轉變為以上`PageData`類型的代碼是相對固定的，不必要在每次分頁查詢時都寫相同的代碼進行轉換，則可以：

- 在`PageData`類上添加使用`PageInfo`參數的構造方法，在構造方法內部封裝必要的數據即可
- 使用新的工具類，在此工具類提供“將`PageInfo`轉換為`PageData`的方法”

以使用工具類為例：

```java
public class PageInfoToPageDataConverter {

    public static <T> PageData<T> convert(PageInfo<T> pageInfo) {
        PageData<T> pageData = new PageData<>();
        return pageData.setList(pageInfo.getList())
                .setPageNum(pageInfo.getPageNum())
                .setPageSize(pageInfo.getPageSize())
                .setTotal(pageInfo.getTotal())
                .setMaxPage(pageInfo.getPages());
    }

}
```

# 根據父級查詢子級類別列表--Repository層

在`ICategoryRepository`中添加抽象方法：

```java
/**
 * 根據父級類別查詢其子級類別列表
 *
 * @param parentId 父級類別的ID
 * @param pageNum  頁碼
 * @param pageSize 每頁數據量
 * @return 類別列表
 */
PageData<CategoryListItemVO> listByParent(Long parentId, Integer pageNum, Integer pageSize);
```

並在`CategoryRepositoryImpl`中實現：

```java
@Override
public PageData<CategoryListItemVO> listByParent(Long parentId, Integer pageNum, Integer pageSize) {
    log.debug("開始執行【根據父級類別查詢其子級類別列表】的數據訪問，父級類別：{}，頁碼：{}，每頁數據量：{}", parentId, pageNum, pageSize);
    PageHelper.startPage(pageNum, pageSize);
    List<CategoryListItemVO> list = categoryMapper.listByParent(parentId);
    PageInfo<CategoryListItemVO> pageInfo = new PageInfo<>(list);
    // PageData<CategoryListItemVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
    // return pageData;
    // 【非常不推薦】return PageInfoToPageDataConverter.convert(new PageInfo<>(categoryMapper.listByParent(parentId)));
    return PageInfoToPageDataConverter.convert(pageInfo);
}
```

完成後，編寫並執行測試：

```java
@Test
void listByParent() {
    Long parentId = 0L;
    Integer pageNum = 1;
    Integer pageSize = 8;
    PageData<?> pageData = repository.listByParent(parentId, pageNum, pageSize);
    System.out.println("頁碼：" + pageData.getPageNum());
    System.out.println("每頁數據量：" + pageData.getPageSize());
    System.out.println("總數據量：" + pageData.getTotal());
    System.out.println("總頁數：" + pageData.getMaxPage());
    System.out.println("列表：" + pageData.getList());
}
```

# 根據父級查詢子級類別列表--Service層

先在`ICategoryService`中聲明抽象方法：

```java
/**
 * 根據父級類別查詢其子級類別列表，每頁的數據量將使用默認值
 *
 * @param parentId 父級類別的ID
 * @param pageNum  頁碼
 * @return 類別列表
 */
PageData<CategoryListItemVO> listByParent(Long parentId, Integer pageNum);

/**
 * 根據父級類別查詢其子級類別列表
 *
 * @param parentId 父級類別的ID
 * @param pageNum  頁碼
 * @param pageSize 每頁數據量
 * @return 類別列表
 */
PageData<CategoryListItemVO> listByParent(Long parentId, Integer pageNum, Integer pageSize);
```

並在`CategoryServiceImpl`中實現以上方法：

```java
@Override
public PageData<CategoryListItemVO> listByParent(Long parentId, Integer pageNum) {
    return categoryRepository.listByParent(parentId, pageNum, 20);
}

@Override
public PageData<CategoryListItemVO> listByParent(Long parentId, Integer pageNum, Integer pageSize) {
    return categoryRepository.listByParent(parentId, pageNum, pageSize);
}
```

# 根據父級查詢子級類別列表--Controller層

在`CategoryController`中處理請求：

```java
@GetMapping("/list-by-parent")
@ApiOperation("根據父級查詢子級列表")
@ApiOperationSupport(order = 420)
@ApiImplicitParams({
        @ApiImplicitParam(name = "parentId", value = "父級類別ID", required = true, paramType = "query", dataType = "long"),
        @ApiImplicitParam(name = "page", value = "頁碼", paramType = "query", defaultValue = "1", dataType = "int"),
        @ApiImplicitParam(name = "queryType", value = "查詢類型", paramType = "query", example = "all")
})
public JsonResult listByParent(@Range(message = "請提交有效的父級類別ID值！") Long parentId,
                               @Range(min = 1, message = "請提交有效的頁碼值！") Integer page,
                               String queryType) {
    Integer pageNum = page == null ? 1 : page;
    PageData<CategoryListItemVO> pageData;
    if ("all".equals(queryType)) {
        pageData = categoryService.listByParent(parentId, pageNum, Integer.MAX_VALUE);
    } else {
        pageData = categoryService.listByParent(parentId, pageNum);
    }
    return JsonResult.ok(pageData);
}
```
