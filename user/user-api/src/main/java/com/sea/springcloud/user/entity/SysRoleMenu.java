package com.sea.springcloud.user.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("角色菜单映射")
public class SysRoleMenu {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 角色ID
     */
    private Integer roleId;
}
