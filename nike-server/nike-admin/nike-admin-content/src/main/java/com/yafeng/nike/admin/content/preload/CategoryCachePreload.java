package com.yafeng.nike.admin.content.preload;

import com.yafeng.nike.admin.content.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Deprecated
@Slf4j
//@Component
public class CategoryCachePreload implements ApplicationRunner {

    @Autowired
    ICategoryService categoryService;

    @Override
    public void run(ApplicationArguments args) throws Exception{
        categoryService.rebuildCache();
    }
}
