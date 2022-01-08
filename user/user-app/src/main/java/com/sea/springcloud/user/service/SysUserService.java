package com.sea.springcloud.user.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sea.springcloud.common.web.common.CommonServiceImpl;
import com.sea.springcloud.user.entity.SysUser;
import com.sea.springcloud.user.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

@Service
public class SysUserService extends CommonServiceImpl<SysUserMapper, SysUser> {
    public SysUser loadUserByUsername(String username) {
        SysUser sysUser= this.list(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername,username)
                .eq(SysUser::getDeleteFlag,false)).stream().findFirst().orElse(null);
        if(sysUser==null){
            throw  new IllegalArgumentException("用户名错误");
        }
        return sysUser;
    }
}
