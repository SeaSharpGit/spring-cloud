package com.sea.springcloud.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sea.springcloud.user.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}
