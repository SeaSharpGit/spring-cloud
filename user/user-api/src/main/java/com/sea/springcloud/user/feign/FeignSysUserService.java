package com.sea.springcloud.user.feign;

import com.sea.springcloud.common.core.constant.FeignServiceConstants;
import com.sea.springcloud.common.core.vo.MyResult;
import com.sea.springcloud.user.entity.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "feignSysUserService",name = FeignServiceConstants.USER)
public interface FeignSysUserService {
    @GetMapping("/user/loadUserByUsername/{username}")
    MyResult<SysUser> loadUserByUsername(@PathVariable("username") String username);

}
