// ======================================
// Project Name:ssm
// Package Name:com.kingyee.starter.common.security
// File Name:UserUtil.java
// Create Date:2019年10月24日  11:02
// ======================================
package com.kingyee.starter.common.security;

import com.kingyee.starter.entity.CrsSysUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class UserUtil {

    public static final String USER_SESSION_NAME = "CRS_LOGIN_SESSION";

    /**
     * 取得session
     */
    private static HttpSession getSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes()).getRequest();
        return request.getSession(true);
    }

    /**
     * 判断用户是否登录
     */
    public static boolean hasLogin() {
        return getSession().getAttribute(USER_SESSION_NAME) != null;
    }

    /**
     * 登录
     */
    public static void login(CrsSysUser user) {
        HttpSession session = getSession();
        Object o = session.getAttribute(USER_SESSION_NAME);
        if (o != null) {
            session.removeAttribute(USER_SESSION_NAME);
        }
        session.setAttribute(USER_SESSION_NAME, user);
    }

    public static void logout() {
        getSession().removeAttribute(USER_SESSION_NAME);
    }


    /**
     * 取得登录用户信息
     */
    public static CrsSysUser getLoginUser() {
        if (hasLogin()) {
            return (CrsSysUser) getSession().getAttribute(USER_SESSION_NAME);
        } else {
            return null;
        }
    }

    /**
     * 取得登录用户的主键
     */
    public static Long getLoginUserId() {
        return hasLogin() ? Objects.requireNonNull(getLoginUser()).getSuPk() : null;
    }

    /**
     * 取得登录用户的姓名
     */
    public static String getLoginUserName() {
        return hasLogin() ? Objects.requireNonNull(getLoginUser()).getSuName() : "";
    }

    /**
     * 当前登录用户是否是管理员
     */
    public static boolean isAdmin() {
        CrsSysUser bean = getLoginUser();
        return bean != null && bean.getSuRole() != null && UserRoleEnum.admin.ordinal() == bean.getSuRole();
    }

}