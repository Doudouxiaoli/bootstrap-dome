// ======================================
// Project Name:ssm
// Package Name:com.kingyee.starter.controller
// File Name:AdminProjectController.java
// Create Date:2019年10月24日  14:12
// ======================================
package com.kingyee.starter.controller;

import com.kingyee.starter.common.security.UserUtil;
import com.kingyee.starter.entity.CrsProject;
import com.kingyee.starter.service.ICrsProjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin/project")
public class AdminProjectController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminProjectController.class);

    private final ICrsProjectService projectService;

    public AdminProjectController(ICrsProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value = {"", "/list"})
    public String list(ModelMap mm, Integer current, Integer size, String keyword) {
        if (current == null || current <= 0) {
            current = 1;
        }
        if (size == null || size <= 0 || size > 100) {
            size = 15;
        }
        try {
            QueryWrapper<CrsProject> queryWrapper = new QueryWrapper<CrsProject>();
            // 查询条件
            if (!StringUtils.isEmpty(keyword)) {
                queryWrapper = queryWrapper
                        .nested(i -> i.like("pro_name", keyword).or().like("pro_batch_number", keyword));
            }
            if (!UserUtil.isAdmin()) {
                queryWrapper = queryWrapper.eq("pro_create_user", UserUtil.getLoginUserId());
            }
            IPage<CrsProject> page = new Page<>(current, size);
            page = projectService.page(page, queryWrapper);
            mm.addAttribute("keyword", keyword);
            mm.addAttribute("page", page);
            return "/admin/project/list";
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return "/error/error";
        }
    }

    @RequestMapping(value = "/detail")
    public String detail(ModelMap mm, Long proPk) {
        try {
            if (proPk != null && proPk > 0) {
                CrsProject project = projectService.getById(proPk);
                mm.addAttribute("model", project);
            } else {
                mm.addAttribute("model", new CrsProject());
            }
            return "/admin/project/detail";
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return "/error/error";
        }
    }

    @RequestMapping(value = "/save")
    public String save(CrsProject project) {
        try {
            projectService.save(project);
            return "redirect:list";
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return "/error/error";
        }
    }
}