package com.sea.springcloud.common.security.config;

import com.sea.springcloud.common.security.annotation.NoAuth;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NoAuthConfig implements InitializingBean {
    private final WebApplicationContext applicationContext;

    @Getter
    private final Map<String, Set<String>> paths = new HashMap<String, Set<String>>() {{
        put("/v3/**", new HashSet<String>() {{
            add("GET");
        }});
        put("/swagger-ui/**", new HashSet<String>() {{
            add("GET");
        }});
        put("/swagger-resources/**", new HashSet<String>() {{
            add("GET");
        }});
//        put("/webjars/**", new HashSet<String>() {{
//            add("GET");
//        }});
    }};

    @Override
    public void afterPropertiesSet() {
        Map<RequestMappingInfo, HandlerMethod> requestMap = applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : requestMap.entrySet()) {
            if ((entry.getValue().getBeanType().isAnnotationPresent(NoAuth.class)
                    || entry.getValue().getMethod().isAnnotationPresent(NoAuth.class))
                    && entry.getKey().getPatternsCondition() != null) {
                Set<String> methods = entry.getKey().getMethodsCondition().getMethods().stream().map(RequestMethod::name).collect(Collectors.toSet());
                for (String pattern : entry.getKey().getPatternsCondition().getPatterns()) {
                    paths.put(pattern, methods);
                }
            }
        }
    }


}
