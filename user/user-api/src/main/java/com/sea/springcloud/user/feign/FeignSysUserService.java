package com.sea.springcloud.user.feign;

import com.sea.springcloud.common.core.constant.FeignConstants;
import com.sea.springcloud.common.core.entity.R;
import com.sea.springcloud.user.vo.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "feignSysUserService",name = FeignConstants.USER)
public interface FeignSysUserService {
    @GetMapping("/user/loadUserByUsername/{username}")
    R<LoginUser> loadUserByUsername(@PathVariable("username") String username);
}
