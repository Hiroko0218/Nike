package cn.tedu.spring.auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserControllerAuto {
    /*
        @Autowired注解：注入對象類型數據(自動裝配)
        1.可以加在屬性上
        2.可以加在setter()方法上
        3.可以加在構造方法上(接口得有唯一實現類,因為@Qualifier註解不能夹在構造方法上)
     */
    @Autowired
    /*
        指定名稱：和接口實現類中的 @Component() 注解中的名稱一致
        如果 @Component 注解中未指定名字,則默認為 類名首字母小寫
     */
    @Qualifier("autoSoft")
    private CacheAuto cacheAuto;

//    //@Autowired
//    public UserControllerAuto(CacheAuto cacheAuto) {
//        this.cacheAuto = cacheAuto;
//    }
//
//    //@Autowired
//    //@Qualifier("softCacheAuto")
//    public void setCacheAuto(CacheAuto cacheAuto) {
//        this.cacheAuto = cacheAuto;
//    }
}
