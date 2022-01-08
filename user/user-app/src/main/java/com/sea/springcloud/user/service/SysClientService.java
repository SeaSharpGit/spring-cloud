package com.sea.springcloud.user.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sea.springcloud.common.web.common.CommonServiceImpl;
import com.sea.springcloud.user.entity.SysClient;
import com.sea.springcloud.user.mapper.SysClientMapper;
import com.sea.springcloud.user.vo.OAuthClientDetails;
import org.springframework.stereotype.Service;

@Service
public class SysClientService extends CommonServiceImpl<SysClientMapper, SysClient> {
    public OAuthClientDetails loadClientByClientId(String clientId) {
        SysClient sysClient = this.list(Wrappers.<SysClient>lambdaQuery().eq(SysClient::getClient, clientId)
                .eq(SysClient::getDeleteFlag, false)).stream().findFirst().orElse(null);
        if (sysClient == null) {
            throw new IllegalArgumentException("用户名错误");
        }
        OAuthClientDetails result = new OAuthClientDetails();
        result.setClientId(sysClient.getClient());
        result.setClientSecret(sysClient.getSecret());
        for (String grantType : sysClient.getGrantType().split(",")) {
            result.getAuthorizedGrantTypes().add(grantType);
        }
        for (String scope : sysClient.getScope().split(",")) {
            result.getAuthorizedGrantTypes().add(scope);
        }
        result.setAccessTokenValiditySeconds(sysClient.getAccessExpire());
        result.setRefreshTokenValiditySeconds(sysClient.getRefreshExpire());
        result.setClientSecret(sysClient.getSecret());
        return result;
    }
}
