package com.yafeng.nike.admin.content.dao.cache.impl;

import com.yafeng.nike.admin.content.dao.cache.ICategoryCacheRepository;
import com.yafeng.nike.admin.content.pojo.vo.CategoryListItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class CategoryCacheRepositoryImpl implements ICategoryCacheRepository {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public void saveList(List<CategoryListItemVO> categoryList) {
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        String key = "categoryList";
        for (CategoryListItemVO category : categoryList) {
            opsForList.rightPush(KEY_CATEGORY_LIST, category);
        }
    }

    @Override
    public boolean deleteList(){
        return redisTemplate.delete(KEY_CATEGORY_LIST);
    }

    @Override
    public List<CategoryListItemVO> list() {
        String key = "categoryList";
        long start = 0;
        long end = -1;
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        List range = opsForList.range(KEY_CATEGORY_LIST, start, end);
        return range;
    }

}
