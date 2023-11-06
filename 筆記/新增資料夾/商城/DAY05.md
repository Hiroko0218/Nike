# MyBatis Plus（續）

如果需要自定義查詢，應該：

- 創建所需的VO類
- 在Mapper接口中聲明抽象方法
- 在XML文件中配置抽象方法映射的SQL語句
- 在配置文件中指定XML文件的位置（一次性配置）

則先在項目的根包（創建項目時就有的包，也可以理解為啟動類所在的包）創建`pojo.vo.CategoryStandardVO`類：

```java
@Data
public class CategoryStandardVO implements Serializable {

    /**
     * 數據ID
     */
    private Long id;

    /**
     * 類別名稱
     */
    private String name;

    /**
     * 父級類別ID，如果無父級，則為0
     */
    private Long parentId;

    /**
     * 深度，最頂級類別的深度為1，次級為2，以此類推
     */
    private Integer depth;

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

    /**
     * 是否啟用，1=啟用，0=未啟用
     */
    private Integer enable;

    /**
     * 是否為父級（是否包含子級），1=是父級，0=不是父級
     */
    private Integer isParent;

    /**
     * 是否顯示在導航欄中，1=啟用，0=未啟用
     */
    private Integer isDisplay;

    /**
     * 數據創建時間
     */
    private LocalDateTime gmtCreate;

    /**
     * 數據最後修改時間
     */
    private LocalDateTime gmtModified;

}
```

提示：以上VO類中包含實體類中所有的屬性，可能存在多余的屬性，可以在後續細化項目功能時再刪除其中多余的屬性。

在`CategoryMapper`中添加抽象方法：

```java
/**
 * 根據ID查詢類別
 * @param id 類別ID
 * @return 匹配的類別，如果沒有匹配的數據，則返回null
 */
CategoryStandardVO getStandardById(Long id);
```

在`src/main/resources`下創建`mapper`文件夾，並在此文件夾中使用XML配置SQL語句：

```mysql
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="cn.tedu.tmall.admin.mall.dao.persist.mapper.CategoryMapper">

    <!-- CategoryStandardVO getStandardById(Long id); -->
    <select id="getStandardById" resultType="">
        SELECT
            id, name, parent_id, depth, keywords,
            sort, icon, enable, is_parent, is_display,
            gmt_create, gmt_modified
        FROM
            mall_category
        WHERE
            id=#{id}
    </select>

</mapper>
```

注意：如果XML文件在`src/main/resources`下名為`mapper`的文件夾下，並不需要在配置文件中指定XML文件的位置，如果使用的文件夾的名稱不是`mapper`（例如使用`mappers`），則必須在配置文件中使用`mybatis-plus.mapper-locations`屬性進行配置，例如：

```yaml
mybatis-plus:
  mapper-locations: classpath:mappers/*.xml
```

# 使用MyBatis Plus處理數據的創建時間和最後修改時間

MyBatis Plus提供了`MetaObjectHandler`接口，允許自定義組件類實現此接口，並重寫方法，以決定自動處理數據的創建時間和最後修改時間！

可以自行創建此組件類：

```java
package cn.tedu.tmall.admin.mall.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 基於MyBatis Plus的自動填充時間的處理器類
 *
 * @author java@tedu.cn
 * @version 2.0
 */
@Slf4j
@Component
public class TimeMetaObjectHandler implements MetaObjectHandler {

    /**
     * 數據創建時間的屬性名
     */
    public static final String FIELD_CREATE_TIME = "gmtCreate";
    /**
     * 數據最後修改時間的屬性名
     */
    public static final String FIELD_UPDATE_TIME = "gmtModified";

    public TimeMetaObjectHandler() {
        log.info("創建MyBatis Plus的自動填充數據的處理器對象：TimeMetaObjectHandler");
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.setFieldValByName(FIELD_CREATE_TIME, now, metaObject);
        this.setFieldValByName(FIELD_UPDATE_TIME, now, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.setFieldValByName(FIELD_UPDATE_TIME, now, metaObject);
    }

}
```

並且，需要在實體類中需要自動填充時間的屬性上添加注解進行配置，例如：

```java
/**
 * 數據創建時間
 */
@TableField(fill = FieldFill.INSERT)
private LocalDateTime gmtCreate;

/**
 * 數據最後修改時間
 */
@TableField(fill = FieldFill.INSERT_UPDATE)
private LocalDateTime gmtModified;
```

# 新增類別 -- Repository 

關於Repository的作用：再議

Repository和Mapper同屬於DAO層的persist之下，所以，在項目中分包時，大多會分為：

```
-- dao
-- -- persist
-- -- -- mapper
-- -- -- repository
```

在實際編寫時，需要自行創建Repository的接口及實現類，此層仍是解決數據訪問的層。

則在項目的根包下創建`dao.persist.repository.ICategoryRepository`接口，並在接口中添加抽象方法：

```java
public interface ICategoryRepository {
    int insert(Category category);
}
```

並且，在`dao.persist.repository.impl.CategoryRepositoryImpl`類，實現以上接口，在類上添加`@Repository`注解，在類中自動裝配Mapper對象，並通過Mapper對象來實現重寫的方法的功能，例如：

```java
@Repository
public class CategoryRepositoryImpl implements ICategoryRepository {
    
    @Autowired
    private CategoryMapper categoryMapper;
    
    @Override
    public int insert(Category category) {
        return categoryMapper.insert(category);
    }
    
}
```

# 新增類別 -- Service

Service層也是由接口和實現類共同完成的，也都是自行開發的！

Service的作用是：設計業務流程與業務邏輯，以保證數據的完整性、有效性、安全性。

關於Service中的方法的聲明原則：

- 返回值類型：僅以操作成功為前提進行設計
  - 如果操作失敗，將通過拋出異常的方式來表示

- 方法名稱：自定義
- 參數列表：取決於客戶端提交的請求參數，另外，還可能包括Controller能獲取的數據，例如當事人信息

關於方法的返回值設計的示例：

```java
User login(String username, String password) throws 用戶名不存在異常, 密碼錯誤異常, 賬號被禁用的異常;
```

```java
try {
    User user = service.login("root", "123456");
	System.out.println("登錄成功，用戶信息：" + user); 
} catch (用戶名不存在異常 e) {
    System.out.println("登錄失敗，用戶名不存在"); 
} catch (密碼錯誤異常 e) {
    System.out.println("登錄失敗，密碼錯誤"); 
} catch (賬號被禁用的異常 e) {
    System.out.println("登錄失敗，賬號被禁用"); 
}
```

在項目的根包下創建`pojo.param.CategoryAddNewParam`類，作為客戶端提交的請求參數類型：

```java
@Data
public class CategoryAddNewParam implements Serializable {

    /**
     * 類別名稱
     */
    private String name;

    /**
     * 父級類別ID，如果無父級，則為0
     */
    private Long parentId;

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

    /**
     * 是否啟用，1=啟用，0=未啟用
     */
    private Integer enable;

    /**
     * 是否顯示在導航欄中，1=啟用，0=未啟用
     */
    private Integer isDisplay;
 
}
```

在項目的根包下創建`service.ICategoryService`接口，並在接口中添加抽象方法：

```java
public interface ICategoryService {
    // 新增類別
    void addNew(CategoryAddNewParam categoryAddNewParam);
}
```

在項目的根包下創建`service.impl.CategoryServiceImpl`類，實現以上接口，並在類上添加`@Service`注解：

```java
@Service
public class CategoryServiceImpl implements ICategoryService {
    
}
```















# 新增類別 -- Service 

# 新增類別 -- Controller 
