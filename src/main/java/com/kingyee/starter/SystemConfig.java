// ======================================
// Project Name:meddb-starter
// Package Name:com.kingyee.starter
// File Name:SystemConfig.java
// Create Date:2019年10月16日  15:54
// ======================================
package com.kingyee.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 注意 set方法为非static
 */
@ConfigurationProperties(prefix = "meddb.system")
public class SystemConfig {


    /**
     * 域名
     */
    private static String domain;

    /**
     * 项目名
     */
    private static String projectName;

    /**
     * dev
     */
    private static boolean debug;

    public static String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        SystemConfig.domain = domain;
    }

    public static String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        SystemConfig.projectName = projectName;
    }

    public static boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        SystemConfig.debug = debug;
    }
}