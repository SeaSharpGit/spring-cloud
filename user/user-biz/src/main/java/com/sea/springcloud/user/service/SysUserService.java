package com.sea.springcloud.user.service;

import com.sea.springcloud.common.web.common.CommonServiceImpl;
import com.sea.springcloud.user.entity.SysUser;
import com.sea.springcloud.user.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

@Service
public class SysUserService extends CommonServiceImpl<SysUserMapper, SysUser> {

}
