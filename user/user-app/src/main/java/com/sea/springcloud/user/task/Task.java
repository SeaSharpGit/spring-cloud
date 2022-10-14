package com.sea.springcloud.user.task;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

@Component
public class Task {
    @XxlJob("test")
    public void test(){


    }

}
