package com.sea.springcloud.user.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sea.springcloud.common.core.vo.LoginUser;
import com.sea.springcloud.common.web.common.CommonServiceImpl;
import com.sea.springcloud.user.entity.SysUser;
import com.sea.springcloud.user.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SysUserService extends CommonServiceImpl<SysUserMapper, SysUser> {

    public LoginUser getLoginUserByUserName(String userName) {
        Optional<SysUser> optional= this.list(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getName,userName)
                .eq(SysUser::getDelFlag,false)).stream().findFirst();
        if(!optional.isPresent()){
            throw  new IllegalArgumentException("用户名错误");
        }
        SysUser user=optional.get();
        LoginUser result=new LoginUser();
        result.setId(user.getId());
        result.setUsername(user.getUserName());
        result.setPassword(user.getPassword());
        result.setEnabled(user.getEnabled());

        return result;
    }
}
