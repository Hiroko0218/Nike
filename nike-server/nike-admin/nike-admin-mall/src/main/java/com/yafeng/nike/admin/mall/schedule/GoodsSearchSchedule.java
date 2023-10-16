package com.yafeng.nike.admin.mall.schedule;

import com.yafeng.nike.admin.mall.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 處理商品搜索數據的計劃任務
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Component
public class GoodsSearchSchedule {

    @Autowired
    private IGoodsService goodsService;

    @Scheduled(cron = "0 30 12 * * ?")
    public void rebuildSearch() {
        log.debug("開始執行【重建商品的搜索數據】計劃任務");
        goodsService.rebuildSearch();
    }

}
