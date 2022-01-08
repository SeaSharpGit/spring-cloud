package com.sea.springcloud.user.feign;

import com.sea.springcloud.common.core.constant.FeignServiceConstants;
import com.sea.springcloud.common.core.vo.MyResult;
import com.sea.springcloud.user.vo.OAuthClientDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "feignSysClientService",name = FeignServiceConstants.USER)
public interface FeignSysClientService {
    @GetMapping("/client/loadClientByClientId/{clientId}")
    MyResult<OAuthClientDetails> loadClientByClientId(@PathVariable("clientId") String clientId);

}
