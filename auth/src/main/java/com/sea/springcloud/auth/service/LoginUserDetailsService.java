package com.sea.springcloud.auth.service;

import com.sea.springcloud.user.feign.FeignUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {

    private final FeignUserService feignUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return feignUserService.getLoginUserByUserName(username).getData();
    }

}
