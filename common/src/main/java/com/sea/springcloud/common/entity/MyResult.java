package com.sea.springcloud.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyResult<T> {
    private int code;
    private T data;

    public static <T> MyResult<T> ok(T data) {
        return new MyResult<>(200, data);
    }

    public static MyResult<String> error(String msg) {
        return new MyResult(500, msg);
    }
}
