package com.sea.springcloud.user.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sea.springcloud.common.web.common.CommonServiceImpl;
import com.sea.springcloud.user.entity.SysUser;
import com.sea.springcloud.user.mapper.SysUserMapper;
import com.sea.springcloud.user.vo.OAuthUserDetails;
import org.springframework.stereotype.Service;

@Service
public class SysUserService extends CommonServiceImpl<SysUserMapper, SysUser> {
    public OAuthUserDetails loadUserByUsername(String username) {
        SysUser sysUser= this.list(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername,username)
                .eq(SysUser::getDeleteFlag,false)).stream().findFirst().orElse(null);
        if(sysUser==null){
            throw  new IllegalArgumentException("用户名错误");
        }
        OAuthUserDetails result=new OAuthUserDetails();
        result.setId(sysUser.getId());
        result.setUsername(sysUser.getUsername());
        result.setPassword(sysUser.getPassword());
        result.setEnabled(sysUser.getEnabled());
        return result;
    }
}
