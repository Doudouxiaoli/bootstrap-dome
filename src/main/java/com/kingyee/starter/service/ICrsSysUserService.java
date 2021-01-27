package com.kingyee.starter.service;

import com.kingyee.starter.entity.CrsSysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-10-16
 */
public interface ICrsSysUserService extends IService<CrsSysUser> {

    void saveNew(CrsSysUser user);

}
