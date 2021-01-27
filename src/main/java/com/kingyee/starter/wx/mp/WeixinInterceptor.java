package com.kingyee.starter.wx.mp;

import com.kingyee.common.util.http.HttpUtil;
import com.kingyee.starter.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信授权拦截器
 */
@Component
public class WeixinInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private MyWeixinHelper myWeixinHelper;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (SystemConfig.isDebug()) {
            WechatUserUtil.initOpenId("");
            return super.preHandle(request, response, handler);
        }
        if (!WechatUserUtil.hasOpenId()) {
            String url = myWeixinHelper.oauthUrl(HttpUtil.getBasePath(request) + "wx/portal/getOpenId?url=" + HttpUtil.getFullUrl(request));
            // 微信用户调用oauth登录
            response.sendRedirect(url);
            return false;
        } else {
            return super.preHandle(request, response, handler);
        }

    }


}