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
     * 客户端
     */
    private String client;

    /**
     * 密码
     */
    private String secret;

    /**
     * 授权类型
     */
    private String grantType;

    /**
     * 域
     */
    private String scope;

    /**
     * AccessToken过期时间（单位：秒）
     */
    private Integer accessExpire;

    /**
     * RefreshToken过期时间（单位：秒）
     */
    private Integer refreshExpire;

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
