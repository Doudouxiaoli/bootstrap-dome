package com.kingyee.starter.wx.mp;


import com.kingyee.common.spring.mvc.WebUtil;

import javax.servlet.http.HttpSession;

/**
 * 用户工具类
 */
public class WechatUserUtil {

    public static final String USER_OPEN_ID = "USER_OPEN_ID";

    /**
     * 取得session
     */
    private static HttpSession getSession() {
        return WebUtil.getOrCreateSession();
    }

    /**
     * 设置微信用户的openId
     */
    public static void initOpenId(String openId) {
        HttpSession session = getSession();
        Object o = session.getAttribute(USER_OPEN_ID);
        if (o != null) {
            session.removeAttribute(USER_OPEN_ID);
        }
        session.setAttribute(USER_OPEN_ID, openId);
    }

    public static String getOpenId() {
        HttpSession session = getSession();
        Object o = session.getAttribute(USER_OPEN_ID);
        if (o != null) {
            return o.toString();
        }
        return null;
    }

    /**
     * 是否有openId
     */
    public static boolean hasOpenId() {
        HttpSession session = getSession();
        Object o = session.getAttribute(USER_OPEN_ID);
        return o != null;
    }

    public static void cleartOpenId() {
        if (hasOpenId()) {
            getSession().removeAttribute(USER_OPEN_ID);
        }
    }

}