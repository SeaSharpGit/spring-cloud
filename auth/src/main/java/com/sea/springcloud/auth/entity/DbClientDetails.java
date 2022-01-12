package com.sea.springcloud.auth.entity;

import com.alibaba.cloud.commons.lang.StringUtils;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

@Data
public class DbClientDetails implements ClientDetails {

    private String clientId;

    private String clientSecret;

    private Set<String> authorizedGrantTypes = new HashSet<>();

    private Set<String> scope = new HashSet<>();

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;


    @Override
    public boolean isSecretRequired(){
        return StringUtils.isNotEmpty(clientSecret);
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

    @Override
    public boolean isAutoApprove(String s) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return new HashMap<>();
    }

}
