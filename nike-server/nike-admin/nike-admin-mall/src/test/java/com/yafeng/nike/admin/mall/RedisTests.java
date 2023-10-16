package com.yafeng.nike.admin.mall;

import cn.hutool.core.bean.BeanUtil;
import com.yafeng.nike.admin.mall.pojo.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTests {

    // 如果需要執行與值相關的數據訪問，需要先通過RedisTemplate得到???Operations類型的對象
    // 例如：操作字符串值時，需要先得到ValueOperation，操作List值時，需要得到ListOperations
    // 如果需要執行的數據訪問與值無關，直接調用RedisTemplate的API即可
    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;

    // 存入字符串類型的值
    // 如果存入中文值，在終端窗口中，會顯示為對應的十六進制值，但並不影響取出數據
    @Test
    void setValue() {
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        opsForValue.set("username", "張三");
        System.out.println("向Redis中寫入數據，完成！");
    }

    // 取出字符串類型的值
    @Test
    void getValue() {
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        Serializable value = opsForValue.get("username");
        System.out.println("向Redis中取出數據：" + value);
    }

    // 存入自定義類型的值，與存入字符串完全相同
    @Test
    void setObjectValue() {
        Category category = new Category();
        category.setId(95L);
        category.setName("Computer");

        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        opsForValue.set("category", category);
        System.out.println("向Redis中寫入自定義類型的數據，完成！");
    }

    // 取出自定義類型的值，與取出字符串完全相同
    @Test
    void getObjectValue() {
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        Serializable value = opsForValue.get("category");
        System.out.println("向Redis中取出數據：" + value);
    }

    // 使用keys命令獲取所有Key
    @Test
    void keys() {
        String pattern = "*"; // keys *
        Set<String> keys = redisTemplate.keys(pattern);
        System.out.println("當前Redis中所有的Key：" + keys);
    }

    // 根據Key刪除數據
    @Test
    void delete() {
        String key = "username";
        Boolean deleteResult = redisTemplate.delete(key);
        System.out.println("刪除數據完成，結果：" + deleteResult);
    }

    // 根據若幹個Key批量刪除數據
    @Test
    void deleteBatch() {
        Set<String> keys = new HashSet<>();
        keys.add("username1");
        keys.add("username3");
        keys.add("username5");

        Long deleteCount = redisTemplate.delete(keys);
        System.out.println("刪除數據完成，成功刪除的數據量：" + deleteCount);
    }

    // 向Redis中存入list數據
    @Test
    void rightPush() {
        List<Category> categoryList = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            Category category = new Category();
            category.setId(i + 0L);
            category.setName("測試類別" + i);
            categoryList.add(category);
        }

        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        String key = "categoryList";
        for (Category category : categoryList) {
            opsForList.rightPush(key, category);
        }
        System.out.println("向Redis中寫入list數據，完成！");
    }

    // 獲取list的長度
    @Test
    void listSize() {
        String key = "categoryList";
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        Long size = opsForList.size(key);
        System.out.println("Redis中Key=" + key + "的list數據的長度為：" + size);
    }

    // 讀取list的區間
    @Test
    void listRange() {
        String key = "categoryList";
        long start = 0;
        long end = -1;
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        List<Serializable> list = opsForList.range(key, start, end);
        for (Serializable serializable : list) {
            System.out.println(serializable);
        }
    }

    // 存入hash類型的數據
    @Test
    void hashPutAll() {
        Category category = new Category();
        category.setId(6998L);
        category.setName("測試類別6998");

        // Map<Object, Object> map = new HashMap<>();
        // map.put("id", category.getId());
        // map.put("name", category.getName());
        Map<String, Object> map = BeanUtil.beanToMap(category);

        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        String key = "mall:category:item:6998";
        opsForHash.putAll(key, map);

        redisTemplate.expire(key, 30, TimeUnit.SECONDS);
    }

    // 修改hash類型的數據
    @Test
    void hashPut() {
        String key = "mall:category:item:1998";
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        opsForHash.put(key, "name", "測試數據666");
    }

    // 讀取Redis中的hash數據
    @Test
    void hashEntries() {
        String key = "mall:category:item:99998";
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        Map<Object, Object> entries = opsForHash.entries(key);
        System.out.println(entries);

        // Category category = new Category();
        // category.setId(Long.valueOf(entries.get("id").toString()));
        // category.setName(entries.get("name").toString());
        Category category = BeanUtil.mapToBean(entries, Category.class, true, null);
        System.out.println(category);
    }

}
