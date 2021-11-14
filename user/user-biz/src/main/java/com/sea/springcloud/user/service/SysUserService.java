package com.sea.springcloud.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sea.springcloud.common.web.service.CommonServiceImpl;
import com.sea.springcloud.user.entity.SysUser;
import com.sea.springcloud.user.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

@Service
public class SysUserService extends CommonServiceImpl<SysUserMapper, SysUser> {

}
