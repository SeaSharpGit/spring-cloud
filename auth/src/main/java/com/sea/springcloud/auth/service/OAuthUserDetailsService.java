package com.sea.springcloud.auth.service;

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
        return feignSysUserService.loadUserByUsername(username).getData();
    }

}
