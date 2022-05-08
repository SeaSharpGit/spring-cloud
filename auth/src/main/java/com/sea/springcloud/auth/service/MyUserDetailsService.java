package com.sea.springcloud.auth.service;

import com.sea.springcloud.common.security.entity.MyUserDetails;
import com.sea.springcloud.user.feign.FeignSysUserService;
import com.sea.springcloud.user.vo.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 替换默认实现：{@link InMemoryUserDetailsManager}
 */
@Component
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final FeignSysUserService feignSysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser loginUser = feignSysUserService.loadUserByUsername(username).getData();
        MyUserDetails result = new MyUserDetails();
        result.setId(loginUser.getId());
        result.setUsername(loginUser.getUsername());
        result.setPassword(loginUser.getPassword());
        result.setEnabled(loginUser.getEnabled());
        List<String> auths = new ArrayList<>();
        loginUser.getRoleIds().forEach(a -> auths.add("ROLE_" + a));
        auths.addAll(loginUser.getAuthorities());
        if (auths.size() > 0) {
            result.setAuthorities(AuthorityUtils.createAuthorityList(auths.toArray(new String[auths.size() - 1])));
        }
        return result;
    }

}
