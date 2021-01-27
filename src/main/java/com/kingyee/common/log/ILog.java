package com.kingyee.common.log;

/**
 * 日志信息接口
 */
public interface ILog {

    /**
     * 模块
     */
    default String getModule() {
        return "";
    }

    /**
     * 日志类型名称
     */
    default String getName() {
        return "";
    }

    /**
     * 日志详细信息
     */
    default String getDetail() {
        return "";
    }

    /**
     * 是否需要记录日志
     */
    default boolean isLogFlag() {
        return true;
    }

    default <T> T getExtend() {
        return null;
    }

}
