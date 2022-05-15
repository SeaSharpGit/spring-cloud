package com.sea.springcloud.user.controller;

import com.sea.springcloud.common.core.entity.R;
import com.sea.springcloud.common.security.AuthEnum;
import com.sea.springcloud.common.security.annotation.Auth;
import com.sea.springcloud.user.entity.SysClient;
import com.sea.springcloud.user.service.SysClientService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
@Api(tags = "客户端管理")
@Auth(AuthEnum.IN)
public class SysClientController {
    private final SysClientService sysClientService;

    @GetMapping("/loadByClientId/{id}")
    public R<SysClient> loadByClientId(@PathVariable("id") String id){
        return R.ok(sysClientService.loadClientByClientId(id));
    }

}
