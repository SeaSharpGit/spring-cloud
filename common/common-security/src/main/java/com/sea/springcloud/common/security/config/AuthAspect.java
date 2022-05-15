package com.sea.springcloud.common.security.config;

import com.sea.springcloud.common.core.constant.MySecurityConstants;
import com.sea.springcloud.common.security.enumeration.AuthEnum;
import com.sea.springcloud.common.security.annotation.Auth;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 内部接口鉴权
 */
@Aspect
@RequiredArgsConstructor
@Component
@Slf4j
public class AuthAspect {
    private final HttpServletRequest request;

    @SneakyThrows
    @Before("@within(auth) || @annotation(auth)")
    public void before(JoinPoint point, Auth auth) {
        // 如果注解在类上，则auth为空，需要获取类上的注解
        if (auth == null) {
            auth = AnnotationUtils.findAnnotation(point.getTarget().getClass(), Auth.class);
        }
        assert auth != null;
        if (auth.value() == AuthEnum.IN) {
            String header = request.getHeader(MySecurityConstants.Auth);
            if (header == null || !header.equals(MySecurityConstants.IN)) {
                throw new AccessDeniedException("不允许访问");
            }
        }
    }
}
