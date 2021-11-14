package com.sea.springcloud.common.web.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {

    /**
     * 删除标识
     */
    private Boolean delFlag;

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
