package com.kingyee.starter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 系统用户
 * </p>
 */
public class CrsSysUser implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "su_pk", type = IdType.AUTO)
    private Long suPk;

    /**
     * 姓名
     */
    private String suName;

    /**
     * 登录名
     */
    private String suLoginName;

    /**
     * 密码
     */
    private String suPasswd;

    /**
     * 角色
     */
    private Integer suRole;

    /**
     * 权限值
     */
    private String suRightText;


    public Long getSuPk() {
        return suPk;
    }

    public void setSuPk(Long suPk) {
        this.suPk = suPk;
    }

    public String getSuName() {
        return suName;
    }

    public void setSuName(String suName) {
        this.suName = suName;
    }

    public String getSuLoginName() {
        return suLoginName;
    }

    public void setSuLoginName(String suLoginName) {
        this.suLoginName = suLoginName;
    }

    public String getSuPasswd() {
        return suPasswd;
    }

    public void setSuPasswd(String suPasswd) {
        this.suPasswd = suPasswd;
    }

    public Integer getSuRole() {
        return suRole;
    }

    public void setSuRole(Integer suRole) {
        this.suRole = suRole;
    }

    public String getSuRightText() {
        return suRightText;
    }

    public void setSuRightText(String suRightText) {
        this.suRightText = suRightText;
    }

    @Override
    public String toString() {
        return "CrsSysUser{" +
        "suPk=" + suPk +
        ", suName=" + suName +
        ", suLoginName=" + suLoginName +
        ", suPasswd=" + suPasswd +
        ", suRole=" + suRole +
        ", suRightText=" + suRightText +
        "}";
    }
}
