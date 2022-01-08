package com.sea.springcloud.user.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sea.springcloud.common.web.common.CommonServiceImpl;
import com.sea.springcloud.user.entity.SysClient;
import com.sea.springcloud.user.mapper.SysClientMapper;
import org.springframework.stereotype.Service;

@Service
public class SysClientService extends CommonServiceImpl<SysClientMapper, SysClient> {
    public SysClient loadClientByClientId(String clientId) {
        SysClient sysClient = this.list(Wrappers.<SysClient>lambdaQuery().eq(SysClient::getClient, clientId)
                .eq(SysClient::getDeleteFlag, false)).stream().findFirst().orElse(null);
        if (sysClient == null) {
            throw new IllegalArgumentException("客户端错误");
        }
        return sysClient;
    }
}
