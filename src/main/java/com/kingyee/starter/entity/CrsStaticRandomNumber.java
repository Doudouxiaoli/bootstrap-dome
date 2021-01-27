package com.kingyee.starter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 静态随机号码表
 * </p>
 */
public class CrsStaticRandomNumber implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "srn_pk", type = IdType.AUTO)
    private Long srnPk;

    /**
     * 项目主键
     */
    private Long srnProPk;

    /**
     * 随机号码
     */
    private Integer srnRandomNumber;

    /**
     * 随机分组
     */
    private Integer srnGroup;

    /**
     * 使用标志
     */
    private Integer srnUseFlag;

    /**
     * 使用时间
     */
    private Long srnUseTime;

    /**
     * 备注
     */
    private String srnMemo;


    public Long getSrnPk() {
        return srnPk;
    }

    public void setSrnPk(Long srnPk) {
        this.srnPk = srnPk;
    }

    public Long getSrnProPk() {
        return srnProPk;
    }

    public void setSrnProPk(Long srnProPk) {
        this.srnProPk = srnProPk;
    }

    public Integer getSrnRandomNumber() {
        return srnRandomNumber;
    }

    public void setSrnRandomNumber(Integer srnRandomNumber) {
        this.srnRandomNumber = srnRandomNumber;
    }

    public Integer getSrnGroup() {
        return srnGroup;
    }

    public void setSrnGroup(Integer srnGroup) {
        this.srnGroup = srnGroup;
    }

    public Integer getSrnUseFlag() {
        return srnUseFlag;
    }

    public void setSrnUseFlag(Integer srnUseFlag) {
        this.srnUseFlag = srnUseFlag;
    }

    public Long getSrnUseTime() {
        return srnUseTime;
    }

    public void setSrnUseTime(Long srnUseTime) {
        this.srnUseTime = srnUseTime;
    }

    public String getSrnMemo() {
        return srnMemo;
    }

    public void setSrnMemo(String srnMemo) {
        this.srnMemo = srnMemo;
    }

    @Override
    public String toString() {
        return "CrsStaticRandomNumber{" +
        "srnPk=" + srnPk +
        ", srnProPk=" + srnProPk +
        ", srnRandomNumber=" + srnRandomNumber +
        ", srnGroup=" + srnGroup +
        ", srnUseFlag=" + srnUseFlag +
        ", srnUseTime=" + srnUseTime +
        ", srnMemo=" + srnMemo +
        "}";
    }
}
