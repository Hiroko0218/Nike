package com.yafeng.nike.admin.content.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
