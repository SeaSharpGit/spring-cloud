package com.sea.springcloud.common.security.util;

import com.sea.springcloud.common.security.entity.UserDetail;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class TokenUtils {
    public UserDetail getUser() {
        return (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
