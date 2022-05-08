package com.sea.springcloud.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sea.springcloud.user.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<String> getMenusByRoleIds(@Param("roleIds") List<Integer> roleIds);
}
