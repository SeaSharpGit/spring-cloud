package com.sea.springcloud.user.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sea.springcloud.common.web.service.CommonServiceImpl;
import com.sea.springcloud.user.entity.SysUser;
import com.sea.springcloud.user.entity.SysUserRole;
import com.sea.springcloud.user.mapper.SysMenuMapper;
import com.sea.springcloud.user.mapper.SysUserMapper;
import com.sea.springcloud.user.mapper.SysUserRoleMapper;
import com.sea.springcloud.user.vo.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysUserService extends CommonServiceImpl<SysUserMapper, SysUser> {
    private final SysMenuMapper sysMenuMapper;
    private final SysUserRoleMapper sysUserRoleMapper;

    public LoginUser loadUserByUsername(String username) {
        SysUser sysUser = list(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username)
                .eq(SysUser::getDeleteFlag, false)).stream().findFirst().orElse(null);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名错误");
        }
        LoginUser result = new LoginUser();
        result.setId(sysUser.getId());
        result.setUsername(sysUser.getUsername());
        result.setPassword(sysUser.getPassword());
        result.setName(sysUser.getName());
        result.setEnabled(sysUser.getEnabled());
        List<Integer> roleIds = sysUserRoleMapper.selectList(Wrappers.<SysUserRole>lambdaQuery()
                        .eq(SysUserRole::getUserId, sysUser.getId()))
                .stream()
                .map(SysUserRole::getRoleId)
                .collect(Collectors.toList());
        result.getRoleIds().addAll(roleIds);
        if (roleIds.size() > 0) {
            result.getAuthorities().addAll(sysMenuMapper.getMenusByRoleIds(roleIds));
        }
        return result;
    }
}
