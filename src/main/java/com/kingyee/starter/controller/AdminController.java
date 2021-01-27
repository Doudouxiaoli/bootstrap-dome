// ======================================
// Project Name:ssm
// Package Name:com.kingyee.starter.controller
// File Name:AdminController.java
// Create Date:2019年10月24日  13:57
// ======================================
package com.kingyee.starter.controller;

import com.kingyee.common.util.EncryptUtil;
import com.kingyee.starter.common.security.UserUtil;
import com.kingyee.starter.entity.CrsSysUser;
import com.kingyee.starter.service.ICrsSysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    private final ICrsSysUserService sysUserService;

    public AdminController(ICrsSysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @RequestMapping(value = {"", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginInit() {
        return "/admin/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(ModelMap mm, String name, String password) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            mm.addAttribute("errMsg", "用户名或密码不能为空！");
            return "/admin/login";
        }
        try {
            QueryWrapper<CrsSysUser> queryWrapper = new QueryWrapper<CrsSysUser>()
                    .eq("su_login_name", name)
                    .eq("su_passwd", EncryptUtil.getSHA256Value(password));
            CrsSysUser user = sysUserService.getOne(queryWrapper);
            if (user == null) {
                mm.addAttribute("errMsg", "用户名或密码输入错误！");
                return "/admin/login";
            }
            UserUtil.login(user);
            return "redirect:/admin/project";
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            mm.addAttribute("errMsg", "出错了~");
            return "/admin/login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        UserUtil.logout();
        return "/admin/login";
    }
}