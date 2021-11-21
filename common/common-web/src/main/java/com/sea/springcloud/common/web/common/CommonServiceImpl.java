package com.sea.springcloud.common.web.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sea.springcloud.common.web.entity.BaseEntity;

public class CommonServiceImpl<M extends BaseMapper<T>,T extends BaseEntity> extends ServiceImpl<M,T> {

}
