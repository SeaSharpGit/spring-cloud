package com.sea.springcloud.user.controller;

import com.sea.springcloud.common.entity.MyResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @GetMapping("/test")
    public MyResult<String> test(){
        return MyResult.ok("success");
    }
}
