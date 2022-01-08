package com.sea.springcloud.auth.service;

import com.sea.springcloud.auth.entity.OAuthUserDetails;
import com.sea.springcloud.user.entity.SysUser;
import com.sea.springcloud.user.feign.FeignSysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthUserDetailsService implements UserDetailsService {
    private final FeignSysUserService feignSysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser=feignSysUserService.loadUserByUsername(username).getData();
        OAuthUserDetails result=new OAuthUserDetails();
        result.setId(sysUser.getId());
        result.setUsername(sysUser.getUsername());
        result.setPassword(sysUser.getPassword());
        result.setEnabled(sysUser.getEnabled());
        return result;
    }

}
