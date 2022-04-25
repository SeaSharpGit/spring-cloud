package com.sea.springcloud.auth.controller;

import com.sea.springcloud.common.core.entity.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test")
    public R<String> test() {
        return R.ok("test");
    }

    @GetMapping("/test2")
    public R<String> test2() {
        return R.ok("test2");
    }

}
