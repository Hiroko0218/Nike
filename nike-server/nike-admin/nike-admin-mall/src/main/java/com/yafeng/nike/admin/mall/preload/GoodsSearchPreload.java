package com.yafeng.nike.admin.mall.preload;

import com.yafeng.nike.admin.mall.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 處理商品搜索數據的預加載
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Component
public class GoodsSearchPreload implements ApplicationRunner {

    @Autowired
    private IGoodsService goodsService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("開始執行【重建商品的搜索數據】的數據預熱");
        goodsService.rebuildSearch();
    }

}
