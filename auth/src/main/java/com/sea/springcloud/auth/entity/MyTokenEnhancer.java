package com.sea.springcloud.auth.entity;

import com.sea.springcloud.common.security.entity.UserDetail;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * token返回值增强
 */
@Component
public class MyTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UserDetail userDetail = (UserDetail) authentication.getUserAuthentication().getPrincipal();
        Map<String, Object> additionals = new HashMap<String, Object>() {{
            put("username", userDetail.getUsername());
        }};
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionals);
        return accessToken;
    }
}
