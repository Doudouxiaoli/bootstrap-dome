// ======================================
// Project Name:meddb-starter
// Package Name:com.kingyee.starter.controller
// File Name:IndexController.java
// Create Date:2019年10月16日  16:38
// ======================================
package com.kingyee.starter.controller;

import com.kingyee.common.jackson.JacksonMapper;
import com.kingyee.common.util.StrUtils;
import com.kingyee.common.util.file.FileBean;
import com.kingyee.common.util.file.FileUtil;
import com.kingyee.starter.entity.CrsSysUser;
import com.kingyee.starter.service.ICrsSysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 接口调用测试
 */
@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    private ICrsSysUserService sysUserService;


    /**
     * json形式
     * 可直接返回 IPage,带分页信息
     *
     * @param mm
     * @return
     */
    @RequestMapping(value = {"/list"})
    public IPage<CrsSysUser> list(ModelMap mm, String keyword, Integer current, Integer size, String sortField, String sortOrder) {
        if (current == null) {
            current = 1;
        }
        QueryWrapper<CrsSysUser> queryWrapper = new QueryWrapper<CrsSysUser>();
        // 查询条件
        if (!StringUtils.isEmpty(keyword)) {
            queryWrapper = queryWrapper.like("su_login_name", keyword).or().like("su_name", keyword);
        }
        if (StrUtils.isNotEmpty(sortField)) {
            queryWrapper.orderBy(true, "ascend".equals(sortOrder) ? true : false, StrUtils.humpToUnderline(sortField));
        }
        IPage<CrsSysUser> page = new Page<>(current, size);
        return sysUserService.page(page, queryWrapper);
    }

    /**
     * 用户数据
     * json形式
     * 返回封装的数据
     *
     * @return
     */
    @RequestMapping(value = {"/load_user"})
    public CrsSysUser loadUser(Long pk) {
        return sysUserService.getById(pk);
    }

    /**
     * 更新字段,跳过null字段,不跳过""字段
     *
     * @return
     */
    @RequestMapping(value = {"/update_user"})
    public JsonNode updateUser(@RequestBody CrsSysUser user) {
        sysUserService.updateById(user);
        return JacksonMapper.newDataInstance(user);
    }

    /**
     * 更新字段,仅指定更新字段(强制:null也会更新)
     *
     * @return
     */
    @RequestMapping(value = {"/update_user2"})
    public JsonNode updateUser2(@RequestBody CrsSysUser user) {
        UpdateWrapper<CrsSysUser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().set(CrsSysUser::getSuName, user.getSuName()).eq(CrsSysUser::getSuPk, user.getSuPk());
        //更新user中字段与wrapper中字段
        //sysUserService.update(user, updateWrapper);
        sysUserService.update(null, updateWrapper);
        return JacksonMapper.newDataInstance(updateWrapper);
    }

    /**
     * 上传
     *
     * @param file
     * @return
     */
    @RequestMapping(value = {"/upload"})
    public FileBean upload(@RequestParam MultipartFile file) {
        return FileUtil.saveFile(file);
    }

}