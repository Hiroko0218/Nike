# 刪除類別 -- Mapper 

（略）

# 刪除類別 -- Repository 

（略，此前的作業）

# 刪除類別 -- Service 

注意：此前的作業完成了基本的刪除功能，但是，未實現業務邏輯！

當嘗試刪除某個類別時，需要設計的業務邏輯可能包括：

- 如果數據不存在（查詢），則不執行刪除
- 如果是父級類別（isParent值為1），則不允許刪除
- 如果有商品關聯到此類別，則不允許刪除
- 如果這是父級中的最後一個子級（基於父級的ID執行統計，且刪除當前後統計結果為0），則將父級的isParent更新為0

實現代碼為：

```java
@Override
public void delete(Long id) {
    log.debug("開始處理【根據ID刪除類別】的業務，參數：{}", id);
    CategoryStandardVO queryResult = categoryRepository.getStandardById(id);
    if (queryResult == null) {
        String message = "刪除類別失敗，嘗試訪問的數據不存在！";
        log.warn(message);
        throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
    }

    if (queryResult.getIsParent().equals(1)) {
        String message = "刪除類別失敗，此類別還包含了子級類別！";
        log.warn(message);
        throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
    }

    int countByCategory = goodsRepository.countByCategory(id);
    if (countByCategory > 0) {
        String message = "刪除類別失敗，仍有商品關聯到了此類別！";
        log.warn(message);
        throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
    }

    int rows = categoryRepository.deleteById(id);
    if (rows != 1) {
        String message = "刪除類別失敗，服務器忙，請稍後再嘗試！";
        log.warn(message);
        throw new ServiceException(ServiceCode.ERROR_DELETE, message);
    }

    Long parentId = queryResult.getParentId();
    int countByParent = categoryRepository.countByParent(parentId);
    if (countByParent == 0) {
        Category updateCategory = new Category();
        updateCategory.setId(parentId);
        updateCategory.setIsParent(0);
        rows = categoryRepository.update(updateCategory);
        if (rows != 1) {
            String message = "刪除類別失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }
}
```

# 刪除類別 -- Controller 

（略，此前的作業）

# 刪除類別 -- 頁面

（略）

# 啟用和禁用 -- Mapper 

（略，直接使用MyBatis Plus的修改數據功能即可）

# 啟用和禁用 -- Repository 

（略，已完成）

# 啟用和禁用 -- Service 

先在`ICategoryService`中添加抽象方法：

```java
int ENABLE_STATE_OFF = 0;
int ENABLE_STATE_ON = 1;
String[] ENABLE_STATE_TEXT = {"禁用", "啟用"};

void setEnable(Long id); // enable > 1
void setDisable(Long id); // enable > 0
```

再在`CategoryServiceImpl`中實現以上方法：

```java
@Override
public void setEnable(Long id) {
    updateEnableById(id, ENABLE_STATE_ON);
}

@Override
public void setDisable(Long id) {
    updateEnableById(id, ENABLE_STATE_OFF);
}

private void updateEnableById(Long id, Integer enable) {
    CategoryStandardVO queryResult = categoryRepository.getStandardById(id);
    if (queryResult == null) {
        String message = ENABLE_STATE_TEXT[enable] + "類別失敗，嘗試訪問的數據不存在！";
        log.warn(message);
        throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
    }

    if (queryResult.getEnable().equals(enable)) {
        String message = ENABLE_STATE_TEXT[enable] + "類別失敗，此類別已經是【" + ENABLE_STATE_TEXT[enable] + "】狀態！";
        log.warn(message);
        throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
    }

    Category updateCategory = new Category();
    updateCategory.setId(id);
    updateCategory.setEnable(enable);
    int rows = categoryRepository.update(updateCategory);
    if (rows != 1) {
        String message = ENABLE_STATE_TEXT[enable] + "類別失敗，服務器忙，請稍後再試！";
        log.warn(message);
        throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
    }
}
```

# 啟用和禁用 -- Controller 

```java
@PostMapping("/{id:[0-9]+}/enable")
@ApiOperation("啟用類別")
@ApiOperationSupport(order = 310)
@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "類別ID", required = true, dataType = "long")
})
public JsonResult setEnable(@Range(min = 1, message = "請提交合法的ID值")
                            @PathVariable Long id) {
    log.debug("開始處理【啟用類別】的請求，參數：{}", id);
    categoryService.setEnable(id);
    return JsonResult.ok();
}

@PostMapping("/{id:[0-9]+}/disable")
@ApiOperation("禁用類別")
@ApiOperationSupport(order = 311)
@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "類別ID", required = true, dataType = "long")
})
public JsonResult setDisable(@Range(min = 1, message = "請提交合法的ID值")
                             @PathVariable Long id) {
    log.debug("開始處理【禁用類別】的請求，參數：{}", id);
    categoryService.setDisable(id);
    return JsonResult.ok();
}
```

# 啟用和禁用 -- 頁面

（略）

# 根據ID查詢類別 -- Mapper 

（略）

# 根據ID查詢類別 -- Repository 

（略，此前的作業）

# 根據ID查詢類別 -- Service 

（略，此前的作業）

# 根據ID查詢類別 -- Controller 

（略，此前的作業）

# 根據ID查詢類別 -- 頁面

（略）

# 修改類別 -- Mapper 

（略）

# 修改類別 -- Repository 

（略）

# 修改類別 -- Service 

在項目的根包下創建`pojo.param.CategoryUpdateInfoParam`類，在類中封裝新數據對應的屬性：

```java
@Data
public class CategoryUpdateInfoParam  implements Serializable {

    /**
     * 類別名稱
     */
    private String name;

    /**
     * 關鍵詞列表，各關鍵詞使用英文的逗號分隔
     */
    private String keywords;

    /**
     * 排序序號
     */
    private Integer sort;

    /**
     * 圖標圖片的URL
     */
    private String icon;

}
```

在`ICategoryService`中添加抽象方法：

```java
void updateInfoById(Long id, CategoryUpdateInfoParam categoryUpdateInfoParam);
```

在`CategoryServiceImpl`中實現以上方法：

```java
@Override
public void updateInfoById(Long id, CategoryUpdateInfoParam categoryUpdateInfoParam) {
    CategoryStandardVO queryResult = categoryRepository.getStandardById(id);
    if (queryResult == null) {
        String message = "修改類別失敗，嘗試訪問的數據不存在！";
        log.warn(message);
        throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
    }

    String name = categoryUpdateInfoParam.getName();
    int count = categoryRepository.countByNameAndNotId(id, name);
    if (count > 0) {
        String message = "修改類別失敗，類別名稱已經被占用！";
        log.warn(message);
        throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
    }

    Category updateCategory = new Category();
    BeanUtils.copyProperties(categoryUpdateInfoParam, updateCategory);
    updateCategory.setId(id);
    int rows = categoryRepository.update(updateCategory);
    if (rows != 1) {
        String message = "修改類別失敗，服務器忙，請稍後再試！";
        log.warn(message);
        throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
    }
}
```

# 修改類別 -- Controller 

```java
@PostMapping("/{id:[0-9]+}/update")
@ApiOperation("修改類別詳情")
@ApiOperationSupport(order = 300)
@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "類別ID", required = true, dataType = "long")
})
public JsonResult setDisable(@PathVariable @Range(min = 1, message = "請提交合法的ID值") Long id,
                             @Valid CategoryUpdateInfoParam categoryUpdateInfoParam) {
    log.debug("開始處理【修改類別詳情】的請求，ID：{}，新數據：{}", id, categoryUpdateInfoParam);
    categoryService.updateInfoById(id, categoryUpdateInfoParam);
    return JsonResult.ok();
}
```

# 修改類別 -- 頁面