package com.sea.springcloud.user.controller;

import com.sea.springcloud.common.core.vo.MyResult;
import com.sea.springcloud.user.entity.SysClient;
import com.sea.springcloud.user.service.SysClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sysClient")
public class SysClientController {
    private final SysClientService sysClientService;

    @GetMapping("/loadClientByClientId/{clientId}")
    public MyResult<SysClient> loadClientByClientId(@PathVariable("clientId") String clientId){
        return MyResult.ok(sysClientService.loadClientByClientId(clientId));
    }


}
