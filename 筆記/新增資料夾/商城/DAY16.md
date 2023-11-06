# 使用Redis緩存數據

把關系型數據庫中的數據存儲到Redis中，並且，後續需要查詢數據時，將優先從Redis中查詢（如果Redis中沒有，則從關系型數據庫中查詢，或，如果Redis中沒有，直接返回沒有數據的結果），這種做法就叫“緩存”數據。

當使用Redis緩存數據後，將存在**數據一致性**的問題！其典型表現就是：如果關系型數據庫中的數據發生了變化，但是，Redis中的數據沒有及時一並調整，就會導致Redis中的數據與關系型數據庫中的數據並不一致！

對於數據一致性問題，首先，要學會區分，你需要的到底是實時一致性，還是最終一致性。

並且，雖然Redis適合緩存大量的數據，但是，如果某些數據的訪問頻率非常低，其實，也沒有必要緩存到Redis中（需要查詢時，直接從關系型數據庫中查詢即可），否則，任何數據都緩存到Redis中，則任何數據都需要處理數據一致性問題。

所以，適合使用Redis緩存的數據應該是：

- 訪問頻率較高，甚至很高
- 對數據一致性要求並不嚴格
- 數據被修改的頻率非常低

關於數據一致問題的解決方案大致有：

- 實時同步：修改關系型數據庫中的數據時，也一並修改Redis中緩存的數據
- 手動同步：修改關系型數據庫中的數據時，不會修改Redis中緩存的數據，僅當管理人員手動操作後，才會將關系型數據庫的數據同步到Redis中
- 定時同步：修改關系型數據庫中的數據時，不會修改Redis中緩存的數據，但每間隔一段時間，或到了某個特定的時間點，就會自動將關系型數據庫的數據同步到Redis中

# 使用Redis緩存資訊的類別列表

首先，使用公共的接口文件定義Redis中的數據的Key值，例如，在`tmall-common`項目中創建此接口：

```java
public interface ContentCacheConsts {

    String KEY_CATEGORY_LIST = "content:category:list";

}
```

需要在`repository`層實現讀寫Redis，則先在項目的根包下創建`dao.cache.ICategoryCacheRepository`接口，此接口應該繼承自以上定義Key值的接口，則此接口的實現類可以直接使用以上Key值，並在接口中聲明抽象方法：

```java
public interface ICategoryCacheRepository extends ContentCacheConsts {

    void saveList(List<CategoryListItemVO> categoryList);

    List<CategoryListItemVO> list();

}
```

在項目的根包下創建`dao.cache.impl.CategoryCacheRepositoryImpl`類，實現以上接口，並重寫接口中定義的抽象方法：

```java
@Repository
public class CategoryCacheRepositoryImpl implements ICategoryCacheRepository {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public void saveList(List<CategoryListItemVO> categoryList) {
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        for (CategoryListItemVO category : categoryList) {
            opsForList.rightPush(KEY_CATEGORY_LIST, category);
        }
    }

    @Override
    public List<CategoryListItemVO> list() {
        long start = 0;
        long end = -1;
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        List range = opsForList.range(KEY_CATEGORY_LIST, start, end);
        return range;
    }

}
```

然後，需要在`service`層調用讀寫Redis的`repository`。

```java
@Override
public PageData<CategoryListItemVO> list(Integer pageNum) {
    log.debug("開始處理【查詢類別列表】的業務，頁碼：{}", pageNum);
    // return categoryRepository.list(pageNum, defaultQueryPageSize);
    List<CategoryListItemVO> list = categoryCacheRepository.list();
    PageData<CategoryListItemVO> pageData = new PageData<>();
    pageData.setList(list);
    pageData.setMaxPage(1);
    pageData.setPageSize(list.size());
    pageData.setTotal(list.size() + 0L);
    pageData.setPageNum(1);
    return pageData;
}
```

經過以上調整後，即可達成新的效果，Controller並不需要做任何調整。

# 使用ApplicationRunner緩存預熱

在Spring Boot項目中，自定義組件類，實現`ApplicationRunner`接口，則重寫的方法會在項目啟動時自動執行，可以在此重寫的方法中向Redis存入緩存的數據，即可實現緩存預熱（啟動項目時即加載緩存數據到Redis中）。

示例代碼：`ICategoryService`：

```java
/**
 * 重建緩存
 */
void rebuildCache();
```

示例代碼：`CategoryServiceImpl`：

```java
@Override
public void rebuildCache() {
    List<CategoryListItemVO> list
            = categoryRepository.list(1, Integer.MAX_VALUE).getList();
    categoryCacheRepository.deleteList();
    categoryCacheRepository.saveList(list);
}
```

示例代碼：`CategoryCachePreload`：

```java
@Slf4j
@Component
public class CategoryCachePreload implements ApplicationRunner {

    @Autowired
    private ICategoryService categoryService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        categoryService.rebuildCache();
    }

}
```

# 計劃任務

在Spring Boot項目中，自定義組件類，並在組件中自定義方法，在方法上添加`@Scheduled`注解並配置計劃任務的執行規則，即可使用計劃任務。

注意：在Spring Boot項目中，默認並不允許執行計劃任務，必須在配置類上添加`@EnableScheduling`注解以開啟，才允許執行計劃任務。

```java
@Slf4j
@Component
public class CategoryCacheSchedule {

    // fixedRate：執行頻率，以【上一次開始執行的時間】來計算下一次的執行時間，以毫秒為單位
    // fixedDelay：執行間隔，以【上一次執行結束的時間】來計算下一次的執行時間，以毫秒為單位
    // cron：使用cron表達式來配置，cron表達式的值是一個字符串，由6~7個域組成，各域之間使用空格分隔
    // -- 在cron表達式中，各域從左至右分別表示：秒 分 時 日 月 周（星期） [年]
    // -- 各域的值可以使用通配符
    // -- 使用星號（*）表示任意值
    // -- 使用問號（?）表示不關心此域的值，僅可以用於“日”和“周”這2個域的值
    // -- 各域的值可以使用 x/y 格式的值，x表示起始值，y表示間隔周期
    // -- 例如在“分”的域位置設置為 1/5，則表示“分”的值為1時開始執行，且每間隔5分鐘執行一次
    // cron表達式示例：
    // "56 34 12 13 2 ? 2023"表示：2023年2月13日12:34:56執行任務，不關心當天星期幾
    // "0/30 * * * * ?"表示：每分鐘的0秒時執行，且每30秒執行一次
    // 更多內容參考：
    // https://segmentfault.com/a/1190000021574315
    // https://blog.csdn.net/study_665/article/details/123506946
    @Scheduled(cron = "0 0 10 ? 8 MON")
    public void rebuildCache() {
        log.debug("CategoryCacheSchedule.rebuildCache()");
    }

}
```

# 關於單點登錄的補充

此前，已經基本實現了單點登錄的效果，當驗證用戶的登錄請求通過時，會將用戶的身份信息（包括：ID、用戶名、權限列表）生成到JWT數據中，並將JWT響應到客戶端，後續，客戶端攜帶JWT發起請求，服務器端通過解析JWT即可識別客戶端的身份（用戶ID是多少？是否具備某些權限？）。

目前仍存在一些問題：

- 將權限列表存入到JWT中，會導致JWT數據特別長，並且，可能泄露某些敏感數據，所以，不應該將權限列表存入到JWT中
- 如果盜用他人的JWT，則可以偽造為他人的身份
- 無法真正的登出（退出登錄）
- 如果管理員把賬號禁用了，此前成功登錄且未過期的用戶仍可以正常訪問

以上問題都可以結合Redis來解決：

- 當驗證登錄通過後，基於用戶的ID（使用用戶ID作為Key中的關鍵數據），將權限列表存入到Redis中，後續，解析JWT得到用戶的ID等信息後，再去Redis中找出對應的權限列表
- 當驗證登錄時，需要獲取客戶端的IP地址、瀏覽器信息甚至設備的關鍵信息等，當驗證登錄通過時，將這些數據存入到Redis中（或存入到JWT中），後續，解析JWT時，檢查後續來訪時的信息與此前存入到Redis中（或JWT中）的是否匹配，如果不匹配，則視為“盜用”行為
- 如果已經完成以上第1項（使用Redis存儲權限列表），當嘗試登出時，刪除Redis中的數據即可，後續，解析JWT得到用戶的ID等信息後，如果Redis中沒有匹配的數據，則視為無效的JWT
- 如果已經完成以上第1項，在存儲權限列表的基礎上，補充存儲用戶的“啟用狀態”，當管理員禁用用戶時，將此“啟用狀態”修改為“禁用”值，後續，解析JWT時，需要在Redis中檢查“啟用狀態”的值

接下來，對代碼的調整大致如下：

- 當驗證登錄通過後（`UserServiceImpl`類中的代碼）：
  - 向JWT中存入的數據調整為：用戶ID、用戶名、IP地址、瀏覽器信息
  - 向Redis中存入數據（`UserStatePO`）：權限列表、啟用狀態
    - 用戶數據的Key：`passport:user-state:{id}`
- 當解析JWT時（Filter中代碼）：
  - 如果解析成功，得到用戶ID後，檢查Redis中是否存在此用戶ID匹配的數據，如果不存在，則過濾器直接放行（不會向`SecurityContext`存入`Authentication`數據）
  - 如果解析成功，從Redis中讀取啟用狀態，如果為“禁用”值，則提示“禁用”，並阻止運行
  - 如果解析成功，從Redis中讀取權限列表，並創建為`Authentication`存入到`SecurityContext`
- 當客戶端發起登出請求時（Controller和Service中的代碼）：
  - 根據當前當事人ID（登錄的用戶ID）檢查Redis中的數據，並刪除Redis中此用戶ID對應的數據
- 當管理員禁用某個用戶時（Service中的代碼）：
  - 根據被禁用的用戶ID查找Redis中的數據，如果Redis中沒有匹配的信息，則不額外執行操作，如果Redis中存在匹配的信息，則將啟用狀態改為“禁用”值

# 關於Redis中的hash類型

在Redis中的hash類型對應Java中的`Map`類型。

建議添加`hutool`依賴，以實現對象與Map的相互轉換：

```xml
<hutool.version>5.8.15</hutool.version>
```

```xml
<!-- hutool：小工具集合 -->
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-all</artifactId>
    <version>${hutool.version}</version>
</dependency>
```

讀寫hash數據示例：

```java
// 存入hash類型的數據
@Test
void hashPutAll() {
    Category category = new Category();
    category.setId(998L);
    category.setName("測試類別998");

    // Map<Object, Object> map = new HashMap<>();
    // map.put("id", category.getId());
    // map.put("name", category.getName());
    Map<String, Object> map = BeanUtil.beanToMap(category);

    HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
    String key = "mall:category:item:998";
    opsForHash.putAll(key, map);
}

// 讀取Redis中的hash數據
@Test
void hashEntries() {
    String key = "mall:category:item:998";
    HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
    Map<Object, Object> entries = opsForHash.entries(key);
    System.out.println(entries);

    // Category category = new Category();
    // category.setId(Long.valueOf(entries.get("id").toString()));
    // category.setName(entries.get("name").toString());
    Category category = BeanUtil.mapToBean(entries, Category.class, true, null);
    System.out.println(category);
}
```
