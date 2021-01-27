// ======================================
// Project Name:meddb-starter
// Package Name:com.kingyee.starter.common
// File Name:ScheduleJobs.java
// Create Date:2019年10月16日  16:06
// ======================================
package com.kingyee.starter.common;

import com.kingyee.starter.SystemConfig;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 */
@Component
public class ScheduleJobs {

    @Scheduled(fixedDelay = 1000L * 60 * 5)
    public void test() {
        System.out.println("演示定时任务->每隔五分钟执行" + SystemConfig.getProjectName());
    }
}