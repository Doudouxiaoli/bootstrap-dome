package com.kingyee.starter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 受试者
 * </p>
 */
public class CrsPatientInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "pi_pk", type = IdType.AUTO)
    private Long piPk;

    /**
     * 受试者姓名缩写
     */
    private String piName;

    /**
     * 受试者编号
     */
    private String piCode;

    /**
     * 医院名称
     */
    private String piHospital;

    /**
     * 入住时间
     */
    private Long piStartTime;

    /**
     * 性别
     */
    private String piSex;

    /**
     * 年龄
     */
    private Integer piAge;

    /**
     * 受试者病例号
     */
    private String piPatientId;

    /**
     * 项目主键
     */
    private Long piProPk;

    /**
     * 扩展信息
     */
    private String piExtendInfo;

    /**
     * 备注
     */
    private String piMemo;

    /**
     * 记录创建时间
     */
    private Long piCreateUser;

    /**
     * 记录创建人
     */
    private Long piCreateTime;

    /**
     * 原发病灶
     */
    private String piPrimaryLesion;

    /**
     * 随机号
     */
    private Integer piRandomNumber;

    /**
     * 随机组
     */
    private Integer piRandomGroup;

    /**
     * 随机分组信息
     */
    private String piRandomGroupJson;

    /**
     * 试验中心编号
     */
    private String piClinicalTrialCode;


    public Long getPiPk() {
        return piPk;
    }

    public void setPiPk(Long piPk) {
        this.piPk = piPk;
    }

    public String getPiName() {
        return piName;
    }

    public void setPiName(String piName) {
        this.piName = piName;
    }

    public String getPiCode() {
        return piCode;
    }

    public void setPiCode(String piCode) {
        this.piCode = piCode;
    }

    public String getPiHospital() {
        return piHospital;
    }

    public void setPiHospital(String piHospital) {
        this.piHospital = piHospital;
    }

    public Long getPiStartTime() {
        return piStartTime;
    }

    public void setPiStartTime(Long piStartTime) {
        this.piStartTime = piStartTime;
    }

    public String getPiSex() {
        return piSex;
    }

    public void setPiSex(String piSex) {
        this.piSex = piSex;
    }

    public Integer getPiAge() {
        return piAge;
    }

    public void setPiAge(Integer piAge) {
        this.piAge = piAge;
    }

    public String getPiPatientId() {
        return piPatientId;
    }

    public void setPiPatientId(String piPatientId) {
        this.piPatientId = piPatientId;
    }

    public Long getPiProPk() {
        return piProPk;
    }

    public void setPiProPk(Long piProPk) {
        this.piProPk = piProPk;
    }

    public String getPiExtendInfo() {
        return piExtendInfo;
    }

    public void setPiExtendInfo(String piExtendInfo) {
        this.piExtendInfo = piExtendInfo;
    }

    public String getPiMemo() {
        return piMemo;
    }

    public void setPiMemo(String piMemo) {
        this.piMemo = piMemo;
    }

    public Long getPiCreateUser() {
        return piCreateUser;
    }

    public void setPiCreateUser(Long piCreateUser) {
        this.piCreateUser = piCreateUser;
    }

    public Long getPiCreateTime() {
        return piCreateTime;
    }

    public void setPiCreateTime(Long piCreateTime) {
        this.piCreateTime = piCreateTime;
    }

    public String getPiPrimaryLesion() {
        return piPrimaryLesion;
    }

    public void setPiPrimaryLesion(String piPrimaryLesion) {
        this.piPrimaryLesion = piPrimaryLesion;
    }

    public Integer getPiRandomNumber() {
        return piRandomNumber;
    }

    public void setPiRandomNumber(Integer piRandomNumber) {
        this.piRandomNumber = piRandomNumber;
    }

    public Integer getPiRandomGroup() {
        return piRandomGroup;
    }

    public void setPiRandomGroup(Integer piRandomGroup) {
        this.piRandomGroup = piRandomGroup;
    }

    public String getPiRandomGroupJson() {
        return piRandomGroupJson;
    }

    public void setPiRandomGroupJson(String piRandomGroupJson) {
        this.piRandomGroupJson = piRandomGroupJson;
    }

    public String getPiClinicalTrialCode() {
        return piClinicalTrialCode;
    }

    public void setPiClinicalTrialCode(String piClinicalTrialCode) {
        this.piClinicalTrialCode = piClinicalTrialCode;
    }

    @Override
    public String toString() {
        return "CrsPatientInfo{" +
        "piPk=" + piPk +
        ", piName=" + piName +
        ", piCode=" + piCode +
        ", piHospital=" + piHospital +
        ", piStartTime=" + piStartTime +
        ", piSex=" + piSex +
        ", piAge=" + piAge +
        ", piPatientId=" + piPatientId +
        ", piProPk=" + piProPk +
        ", piExtendInfo=" + piExtendInfo +
        ", piMemo=" + piMemo +
        ", piCreateUser=" + piCreateUser +
        ", piCreateTime=" + piCreateTime +
        ", piPrimaryLesion=" + piPrimaryLesion +
        ", piRandomNumber=" + piRandomNumber +
        ", piRandomGroup=" + piRandomGroup +
        ", piRandomGroupJson=" + piRandomGroupJson +
        ", piClinicalTrialCode=" + piClinicalTrialCode +
        "}";
    }
}
