package com.sea.springcloud.common.security.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 替换默认实现：{@link RemoteTokenServices}
 */
@RequiredArgsConstructor
public class MyResourceServerTokenServices implements ResourceServerTokenServices {
    private final RedisTokenStore redisTokenStore;

    @SneakyThrows
    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) {
        OAuth2Authentication oAuth2Authentication = redisTokenStore.readAuthentication(accessToken);
        if (oAuth2Authentication == null) {
            return null;
        }
        //TODO:获取到的权限可能不是最新的
//        MyUserDetails myUserDetails = (MyUserDetails) oAuth2Authentication.getPrincipal();
        oAuth2Authentication.setAuthenticated(true);
        return oAuth2Authentication;
    }

    /**
     * 用于授权服务器端，处理token验证
     * 资源服务器不需要此方法
     */
    @Override
    public OAuth2AccessToken readAccessToken(String s) {
        throw new UnsupportedOperationException("MyResourceServerTokenServices.readAccessToken");
    }
}
