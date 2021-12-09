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

    public void resetCreate(int userId){
        LocalDateTime now=LocalDateTime.now();
        this.setCreateTime(now);
        this.setUpdateTime(now);
        this.setCreateUserId(userId);
        this.setUpdateUserId(userId);
    }

    public void resetUpdate(int userId){
        this.setUpdateTime(LocalDateTime.now());
        this.setUpdateUserId(userId);
    }

    public void resetDelete(int userId){
        this.setDelFlag(true);
        resetUpdate(userId);
    }

}
