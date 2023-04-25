package com.example.config;

import com.example.auth.service.users.UserAuthorizationService;
import com.example.filter.AuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {
    // custom authorization filter
    private final UserAuthorizationService userAuthorizationService;
    @Bean  // filter를 빈으로 등록
    public FilterRegistrationBean authFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new AuthFilter(userAuthorizationService));
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);  // filter 동작 순서 정하는 것
        return registrationBean;
    }
}
