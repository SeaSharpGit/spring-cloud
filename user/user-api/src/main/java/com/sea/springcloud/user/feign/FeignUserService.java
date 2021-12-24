package com.sea.springcloud.user.feign;

import com.sea.springcloud.common.core.constant.FeignServiceConstants;
import com.sea.springcloud.common.core.vo.LoginUser;
import com.sea.springcloud.common.core.vo.MyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = FeignServiceConstants.USER)
public interface FeignUserService {

    @GetMapping("/user/getLoginUserByUsername/{username}")
    MyResult<LoginUser> getLoginUserByUsername(@PathVariable("username") String username);

}
