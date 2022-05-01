package com.sea.springcloud.common.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> {
    private int code;
    private T data;
    private String msg;

    public static <T> R<T> ok(T data) {
        return new R<>(0, data, null);
    }

    public static R<Object> error(String msg) {
        return new R<>(1, null, msg);
    }
}
