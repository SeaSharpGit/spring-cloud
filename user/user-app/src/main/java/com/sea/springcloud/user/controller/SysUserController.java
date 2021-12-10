package com.sea.springcloud.user.controller;

import com.sea.springcloud.common.web.entity.MyResult;
import com.sea.springcloud.user.entity.SysUser;
import com.sea.springcloud.user.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class SysUserController {

    private final SysUserService sysUserService;

    @GetMapping("/test")
    public MyResult<SysUser> test() {
        SysUser sysUser = sysUserService.getById(1);
        return MyResult.ok(sysUser);
    }

    @GetMapping("/error")
    public void error() {
        throw new RuntimeException("测试一下错误");
    }


}
