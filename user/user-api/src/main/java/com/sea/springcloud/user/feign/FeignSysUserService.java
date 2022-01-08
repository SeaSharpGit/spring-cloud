package com.sea.springcloud.user.feign;

import com.sea.springcloud.common.core.constant.FeignServiceConstants;
import com.sea.springcloud.common.core.vo.MyResult;
import com.sea.springcloud.user.vo.OAuthUserDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "feignSysUserService",name = FeignServiceConstants.USER)
public interface FeignSysUserService {
    @GetMapping("/sysUser/loadUserByUsername/{username}")
    MyResult<OAuthUserDetails> loadUserByUsername(@PathVariable("username") String username);

}
