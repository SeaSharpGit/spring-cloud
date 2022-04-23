package com.sea.springcloud.common.security.config;

import com.sea.springcloud.common.security.annotation.NoAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class NoAuthConfig implements InitializingBean {
    private final WebApplicationContext applicationContext;
    private static final Pattern PATTERN = Pattern.compile("\\{(.*?)}");

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
                    //处理url中存在/{id}的情况
                    String url = PATTERN.matcher(pattern).replaceAll("*");
                    paths.put(url, methods);
                }
            }
        }
    }

    /**
     * 跳过OAuth2鉴权
     */
    public void permitAll(HttpSecurity http) throws Exception {
        for (Map.Entry<String, Set<String>> entry : paths.entrySet()) {
            for (String httpMethod : entry.getValue()) {
                http.authorizeRequests().antMatchers(HttpMethod.valueOf(httpMethod), entry.getKey()).permitAll();
            }
        }
    }
}
