// ======================================
// Project Name:ssm
// Package Name:com.kingyee.starter.common.security
// File Name:LoginInterceptor.java
// Create Date:2019年10月24日  11:13
// ======================================
package com.kingyee.starter.common.security;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AdminInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        String path = request.getContextPath();
        HttpSession session = request.getSession(true);
        if (session.getAttribute(UserUtil.USER_SESSION_NAME) == null) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return false;
        }
        return super.preHandle(request, response, handler);
    }

}