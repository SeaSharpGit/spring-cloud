package com.sea.springcloud.user.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("客户端")
public class SysClient extends Model<SysClient> {
    /**
     * 客户端账号
     */
    private String id;

    /**
     * 客户端密码
     */
    private String secret;

    /**
     * 授权类型
     */
    private String authorizedGrantTypes;

    /**
     * 域
     */
    private String scope;

    /**
     * AccessToken过期时间
     */
    private Integer accessTokenValiditySeconds;

    /**
     * RefreshToken过期时间
     */
    private Integer refreshTokenValiditySeconds;

    /**
     * 删除标识
     */
    private Boolean deleteFlag;

    /**
     * 创建人ID
     */
    private Integer createUserId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人ID
     */
    private Integer updateUserId;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;



}
