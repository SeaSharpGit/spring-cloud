package com.sea.springcloud.common.web.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class CommonServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

}
