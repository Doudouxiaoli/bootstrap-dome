package com.kingyee.starter.service.impl;

import com.kingyee.starter.entity.CrsSysUser;
import com.kingyee.starter.mapper.CrsSysUserMapper;
import com.kingyee.starter.service.ICrsSysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-10-16
 */
@Service
public class CrsSysUserServiceImpl extends ServiceImpl<CrsSysUserMapper, CrsSysUser> implements ICrsSysUserService {

    /**
     * 事务测试
     *
     * @param user 用户
     */
    public void saveNew(CrsSysUser user) {
        save(user);
        if (Objects.equals("exception", user.getSuLoginName())) {
            throw new RuntimeException("异常处理");
        }
    }

}
