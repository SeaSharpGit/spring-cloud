package com.sea.springcloud.auth.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

@Data
public class ClientDetail implements ClientDetails {
    private String clientId;

    private String clientSecret;

    private Set<String> authorizedGrantTypes = new HashSet<>();

    private Set<String> scope = new HashSet<>();

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;


    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public boolean isScoped() {
        return scope.size() > 0;
    }

    @Override
    public Set<String> getResourceIds() {
        return new HashSet<>();
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return new HashSet<>();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

    /**
     * 是否自动放行
     * true：直接返回授权码；false：跳转到授权页面手动授权
     */
    @Override
    public boolean isAutoApprove(String s) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return new HashMap<>();
    }

}
