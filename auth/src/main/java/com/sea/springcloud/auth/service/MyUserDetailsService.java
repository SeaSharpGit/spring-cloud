package com.sea.springcloud.auth.service;

import com.sea.springcloud.auth.entity.MyUserDetails;
import com.sea.springcloud.user.entity.SysUser;
import com.sea.springcloud.user.feign.FeignSysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

/**
 * 替换默认实现：{@link InMemoryUserDetailsManager}
 */
@Component
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final FeignSysUserService feignSysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser=feignSysUserService.loadUserByUsername(username).getData();
        MyUserDetails result=new MyUserDetails();
        result.setId(sysUser.getId());
        result.setUsername(sysUser.getUsername());
        result.setPassword(sysUser.getPassword());
        result.setEnabled(sysUser.getEnabled());
        return result;
    }

}
