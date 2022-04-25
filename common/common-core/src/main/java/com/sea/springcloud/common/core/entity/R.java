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

    public static <T> R<T> ok(T data) {
        return new R<>(0, data);
    }

    public static R<String> error(String msg) {
        return new R<>(1, msg);
    }
}
