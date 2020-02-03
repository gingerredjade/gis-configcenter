package com.gis.config.client.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务测试类
 *
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://126.com">Hongyu Jiang</a>
 */
@Component
public class SchedulingTest {
    private static final Logger logger = LoggerFactory.getLogger(SchedulingTest.class);

    /**
     * 每5秒执行一次
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void scheduler() {
        logger.info(">>>>>>>>>>>>> scheduled test... ");
    }
}
