// ======================================
// Project Name:meddb-starter
// Package Name:com.kingyee.starter.controller
// File Name:IndexController.java
// Create Date:2019年10月16日  16:38
// ======================================
package com.kingyee.starter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.JsonNode;
import com.kingyee.common.jackson.JacksonMapper;
import com.kingyee.common.medlive.MedliveAuth;
import com.kingyee.common.medlive.MedliveUserBean;
import com.kingyee.starter.SystemConfig;
import com.kingyee.starter.entity.CrsSysUser;
import com.kingyee.starter.service.ICrsSysUserService;
import com.kingyee.starter.wx.mp.config.WxMpProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 入口
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    private WxMpProperties wxMpProperties;

    private final ICrsSysUserService sysUserService;

    public IndexController(ICrsSysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Autowired
    private static SystemConfig systemConfig;

    @RequestMapping(value = {"/index", ""})
    public String index(ModelMap mm) {
        return "redirect:" + MedliveAuth.getAuthUrl(systemConfig.getDomain() + "/auth");
    }

    @RequestMapping(value = {"/detail"})
    public String detail(ModelMap mm) {
        return "wechat/detail";
    }

    /**
     * json形式
     * 可直接返回 IPage,带分页信息
     *
     * @param mm
     * @return
     */
    @RequestMapping(value = {"/list"})
    @ResponseBody
    public IPage<CrsSysUser> list(ModelMap mm, String keyword, Integer current, Integer size) {
        if (current == null) {
            current = 1;
        }
        QueryWrapper<CrsSysUser> queryWrapper = null;
        // 查询条件
        if (!StringUtils.isEmpty(keyword)) {
            queryWrapper = new QueryWrapper<CrsSysUser>().like("su_login_name", keyword).or().like("su_name", keyword);
        }
        IPage<CrsSysUser> page = new Page<>(current, size);
        return sysUserService.page(page, queryWrapper);
    }

    /**
     * json形式
     * 返回封装的数据
     *
     * @param mm
     * @return
     */
    @RequestMapping(value = {"/list2"})
    @ResponseBody
    public JsonNode list2(ModelMap mm) {
        QueryWrapper<CrsSysUser> queryWrapper = null;
        // 查询条件
        // queryWrapper = new QueryWrapper<CrsSysUser>().like("su_login_name","comm");
        IPage<CrsSysUser> page = new Page<>(0, 2);
        page = sysUserService.page(page, queryWrapper);
        return JacksonMapper.newCountInstance(page);
    }

    /**
     * 医脉通用户授权回调后重新发起新请求
     *
     * @param mm
     * @param userinfo
     * @return
     */
    @RequestMapping(value = {"/auth"})
    public String auth(ModelMap mm, String userinfo) {
        return "redirect:" + MedliveAuth.getUserInfoUrl(systemConfig.getDomain() + "/userinfo");
    }

    /**
     * 得到医脉通登录的用户信息
     *
     * @param mm
     * @param userinfo
     * @return
     */
    @RequestMapping(value = {"/userinfo"})
    public String userinfo(ModelMap mm, String userinfo) {
        MedliveUserBean user = MedliveAuth.decryptUserInfo(userinfo);
        //处理业务逻辑
        mm.addAttribute("user", user);
        return "user";
    }


}