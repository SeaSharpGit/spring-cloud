package com.sea.springcloud.user.controller;

import com.sea.springcloud.common.core.entity.R;
import com.sea.springcloud.common.security.enums.AuthEnum;
import com.sea.springcloud.common.security.annotation.Auth;
import com.sea.springcloud.common.sms.util.SmsUtils;
import com.sea.springcloud.user.entity.SysUser;
import com.sea.springcloud.user.service.SysUserService;
import com.sea.springcloud.user.vo.LoginUser;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Api(tags = "用户管理")
public class SysUserController {
    private final SysUserService sysUserService;
    private final SmsUtils smsUtils;

    @Auth(AuthEnum.IN)
    @GetMapping("/loadUserByUsername/{username}")
    public R<LoginUser> loadUserByUsername(@PathVariable("username") String username) {
        return R.ok(sysUserService.loadUserByUsername(username));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('user')")
    public R<SysUser> getById(@PathVariable int id) {
        return R.ok(sysUserService.getById(id));
    }

    @Auth(AuthEnum.NO_AUTH)
    @GetMapping("/noAuth")
    public R<String> noAuth() {
        return R.ok("noAuth");
    }

    @GetMapping("/sms")
    @PreAuthorize("hasAnyAuthority('sms')")
    public void sms() {
        try {
            smsUtils.sendMsg("18912387311", "SMS_223587757",
                    "username", "admin",
                    "password", "123456");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


}
