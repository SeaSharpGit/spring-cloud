package com.sea.springcloud.common.security.util;

import com.sea.springcloud.common.security.entity.MyUserDetails;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class TokenUtils {
    public MyUserDetails getUser() {
        return (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
