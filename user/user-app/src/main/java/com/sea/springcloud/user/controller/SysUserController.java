package com.sea.springcloud.user.controller;

import com.sea.springcloud.common.core.vo.MyResult;
import com.sea.springcloud.common.security.annotation.NoAuth;
import com.sea.springcloud.common.sms.util.SmsUtils;
import com.sea.springcloud.user.entity.SysUser;
import com.sea.springcloud.user.service.SysUserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Api(value = "用户管理",tags = "用户管理")
public class SysUserController {
    private final SysUserService sysUserService;
    private final SmsUtils smsUtils;

    @NoAuth
    @GetMapping("/test")
    public MyResult<SysUser> test() {
        SysUser sysUser = sysUserService.getById(1);
        return MyResult.ok(sysUser);
    }

    @GetMapping("/error")
    public void error() {
        throw new RuntimeException("测试一下错误");
    }

    @GetMapping("/sms")
    public void sms() {
        try {
            smsUtils.sendMsg("18912387311","SMS_223587757",
                    "username","admin",
                    "password","123456");
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @NoAuth
    @GetMapping("/loadUserByUsername/{username}")
    public MyResult<SysUser> loadUserByUsername(@PathVariable("username") String username){
        return MyResult.ok(sysUserService.loadUserByUsername(username));
    }


}
