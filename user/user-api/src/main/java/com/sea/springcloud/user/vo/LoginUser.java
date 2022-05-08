package com.sea.springcloud.user.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class LoginUser {
    @ApiModelProperty("用户ID")
    private Integer id;

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("可用标识")
    private Boolean enabled;

    @ApiModelProperty("角色和权限集合")
    private Set<Integer> roleIds = new HashSet<>();

    @ApiModelProperty("角色和权限集合")
    private Set<String> authorities = new HashSet<>();
}
