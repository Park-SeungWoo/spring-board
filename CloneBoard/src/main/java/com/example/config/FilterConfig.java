package com.example.config;

import com.example.cloneboard.jwt.JwtProvider;
import com.example.filter.jwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {
    // custom authorization filter
    // spring security 쓰면 이부분 필요 없음
    private final JwtProvider jwtProvider;
    @Bean  // filter를 빈으로 등록
    public FilterRegistrationBean authFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new jwtAuthFilter(jwtProvider));
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);  // filter 동작 순서 정하는 것
        return registrationBean;
    }
}
