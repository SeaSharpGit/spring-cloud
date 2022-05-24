package com.sea.springcloud.common.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDetail implements UserDetails {
    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否启用
     */
    private boolean enabled;

    /**
     * 账号未过期
     */
    private boolean accountNonExpired = true;

    /**
     * 账号未锁定
     */
    private boolean accountNonLocked = true;

    /**
     * 密码未过期
     */
    private boolean credentialsNonExpired = true;

    /**
     * 角色和权限集合
     */
    private List<? extends GrantedAuthority> authorities = new ArrayList<>();

}
