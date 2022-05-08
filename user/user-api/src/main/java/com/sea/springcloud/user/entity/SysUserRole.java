package com.sea.springcloud.user.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("用户角色映射")
public class SysUserRole {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 角色ID
     */
    private Integer roleId;
}
