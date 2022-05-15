package com.sea.springcloud.common.security.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthEnum {
    NO_AUTH("不鉴权"),
    IN("内部请求");

    private String name;
}
