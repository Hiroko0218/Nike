package com.yafeng.nike.basic.startup;

import com.yafeng.nike.basic.service.IDistrictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 市區數據的緩存預熱類
 *
 * @author java@yafeng.com
 * @version 2.0
 **/
@Slf4j
@Component
public class DistrictRunner implements ApplicationRunner {

    @Autowired
    private IDistrictService districtService;

    public DistrictRunner() {
        log.debug("創建ApplicationRunner對象：DistrictRunner");
    }

    @Override
    public void run(ApplicationArguments args) {
        log.debug("開始處理【市區數據緩存預熱】，無參數");
        districtService.rebuildCache();
    }

}
