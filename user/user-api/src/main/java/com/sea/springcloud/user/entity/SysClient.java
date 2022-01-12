package com.sea.springcloud.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysClient extends Model<SysClient> {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 客户端账号
     */
    private String clientId;

    /**
     * 客户端密码
     */
    private String clientSecret;

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
