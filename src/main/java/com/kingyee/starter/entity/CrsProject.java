package com.kingyee.starter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 项目表
 * </p>
 */
public class CrsProject implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 项目主键
     */
    @TableId(value = "pro_pk", type = IdType.AUTO)
    private Long proPk;

    /**
     * 名称
     */
    private String proName;

    /**
     * 临床实验批号
     */
    private String proBatchNumber;

    /**
     * 负责人
     */
    private String proBosshead;

    /**
     * 临床实验负责人
     */
    private String proClinicalTrialBosshead;

    /**
     * 启动时间
     */
    private Long proStartTime;

    /**
     * 预计完成时间
     */
    private Long proEndTime;

    /**
     * 临床实验病例
     */
    private Integer proPatientCount;

    /**
     * 分组数
     */
    private Integer proGroupCount;

    /**
     * 因素水平
     */
    private Integer proFactorLevel;

    /**
     * 多中心标志
     */
    private Integer proMulticenterFlag;

    /**
     * 分组选择
     */
    private Integer proGroupMethod;

    /**
     * 临床实验机构
     */
    private String proClinicalTrialOrganization;

    /**
     * 项目创建时间
     */
    private Long proCreateTime;

    /**
     * 项目创建人
     */
    private Long proCreateUser;

    /**
     * 备注
     */
    private String proMemo;

    /**
     * 分组状态
     */
    private Integer proGroupState;

    /**
     * 实验中心编号
     */
    private String proClinicalTrialCode;


    public Long getProPk() {
        return proPk;
    }

    public void setProPk(Long proPk) {
        this.proPk = proPk;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProBatchNumber() {
        return proBatchNumber;
    }

    public void setProBatchNumber(String proBatchNumber) {
        this.proBatchNumber = proBatchNumber;
    }

    public String getProBosshead() {
        return proBosshead;
    }

    public void setProBosshead(String proBosshead) {
        this.proBosshead = proBosshead;
    }

    public String getProClinicalTrialBosshead() {
        return proClinicalTrialBosshead;
    }

    public void setProClinicalTrialBosshead(String proClinicalTrialBosshead) {
        this.proClinicalTrialBosshead = proClinicalTrialBosshead;
    }

    public Long getProStartTime() {
        return proStartTime;
    }

    public void setProStartTime(Long proStartTime) {
        this.proStartTime = proStartTime;
    }

    public Long getProEndTime() {
        return proEndTime;
    }

    public void setProEndTime(Long proEndTime) {
        this.proEndTime = proEndTime;
    }

    public Integer getProPatientCount() {
        return proPatientCount;
    }

    public void setProPatientCount(Integer proPatientCount) {
        this.proPatientCount = proPatientCount;
    }

    public Integer getProGroupCount() {
        return proGroupCount;
    }

    public void setProGroupCount(Integer proGroupCount) {
        this.proGroupCount = proGroupCount;
    }

    public Integer getProFactorLevel() {
        return proFactorLevel;
    }

    public void setProFactorLevel(Integer proFactorLevel) {
        this.proFactorLevel = proFactorLevel;
    }

    public Integer getProMulticenterFlag() {
        return proMulticenterFlag;
    }

    public void setProMulticenterFlag(Integer proMulticenterFlag) {
        this.proMulticenterFlag = proMulticenterFlag;
    }

    public Integer getProGroupMethod() {
        return proGroupMethod;
    }

    public void setProGroupMethod(Integer proGroupMethod) {
        this.proGroupMethod = proGroupMethod;
    }

    public String getProClinicalTrialOrganization() {
        return proClinicalTrialOrganization;
    }

    public void setProClinicalTrialOrganization(String proClinicalTrialOrganization) {
        this.proClinicalTrialOrganization = proClinicalTrialOrganization;
    }

    public Long getProCreateTime() {
        return proCreateTime;
    }

    public void setProCreateTime(Long proCreateTime) {
        this.proCreateTime = proCreateTime;
    }

    public Long getProCreateUser() {
        return proCreateUser;
    }

    public void setProCreateUser(Long proCreateUser) {
        this.proCreateUser = proCreateUser;
    }

    public String getProMemo() {
        return proMemo;
    }

    public void setProMemo(String proMemo) {
        this.proMemo = proMemo;
    }

    public Integer getProGroupState() {
        return proGroupState;
    }

    public void setProGroupState(Integer proGroupState) {
        this.proGroupState = proGroupState;
    }

    public String getProClinicalTrialCode() {
        return proClinicalTrialCode;
    }

    public void setProClinicalTrialCode(String proClinicalTrialCode) {
        this.proClinicalTrialCode = proClinicalTrialCode;
    }

    @Override
    public String toString() {
        return "CrsProject{" +
        "proPk=" + proPk +
        ", proName=" + proName +
        ", proBatchNumber=" + proBatchNumber +
        ", proBosshead=" + proBosshead +
        ", proClinicalTrialBosshead=" + proClinicalTrialBosshead +
        ", proStartTime=" + proStartTime +
        ", proEndTime=" + proEndTime +
        ", proPatientCount=" + proPatientCount +
        ", proGroupCount=" + proGroupCount +
        ", proFactorLevel=" + proFactorLevel +
        ", proMulticenterFlag=" + proMulticenterFlag +
        ", proGroupMethod=" + proGroupMethod +
        ", proClinicalTrialOrganization=" + proClinicalTrialOrganization +
        ", proCreateTime=" + proCreateTime +
        ", proCreateUser=" + proCreateUser +
        ", proMemo=" + proMemo +
        ", proGroupState=" + proGroupState +
        ", proClinicalTrialCode=" + proClinicalTrialCode +
        "}";
    }
}
