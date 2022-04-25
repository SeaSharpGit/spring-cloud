package com.sea.springcloud.user.controller;

import com.sea.springcloud.common.core.entity.R;
import com.sea.springcloud.common.security.annotation.NoAuth;
import com.sea.springcloud.user.entity.SysClient;
import com.sea.springcloud.user.service.SysClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class SysClientController {
    private final SysClientService sysClientService;

    @NoAuth
    @GetMapping("/loadByClientId/{id}")
    public R<SysClient> loadByClientId(@PathVariable("id") String id){
        return R.ok(sysClientService.loadClientByClientId(id));
    }

}
