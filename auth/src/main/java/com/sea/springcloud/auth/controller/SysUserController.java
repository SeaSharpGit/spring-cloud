package com.sea.springcloud.auth.controller;

import com.sea.springcloud.common.core.vo.MyResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class SysUserController {

    @GetMapping("/test")
    public MyResult<String> test() {
        return MyResult.ok("test");
    }

    @GetMapping("/test2")
    public MyResult<String> test2() {
        return MyResult.ok("test2");
    }

}
