package cn.tedu.spring.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * 1.根據名稱進行匹配: @Resource(name="xxx") 和 @Component(value="xxx")
 * 2.未指定 name參數,則根據屬性名作为 name進行匹配
 *   把 cacheRes改為 softCacheRes試試
 * 3.如果屬性名也不一致,則根據類型進行 CacheRes匹配
 *   3.1 把屬性名改回: cacheRes
 *   3.2 直接執行測試方法,裝配失敗(2個實現類)
 *   3.3 註釋掉其中一個實現類的 @Component註解,再次執行測試方法,裝配成功
 */

@Component
public class UserControllerRes {
    @Resource(name="softCacheRes")
    private CacheRes cacheRes;
}
