package com.sea.springcloud.user.feign;

import com.sea.springcloud.common.core.constant.FeignConstants;
import com.sea.springcloud.common.core.constant.MySecurityConstants;
import com.sea.springcloud.common.core.entity.R;
import com.sea.springcloud.user.entity.SysClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(contextId = "feignSysClientService", name = FeignConstants.USER)
public interface FeignSysClientService {
    @GetMapping("/client/loadByClientId/{clientId}")
    R<SysClient> loadClientById(@PathVariable("clientId") String clientId, @RequestHeader(MySecurityConstants.Auth) String auth);

}
