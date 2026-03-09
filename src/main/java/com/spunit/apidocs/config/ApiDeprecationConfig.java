package com.spunit.apidocs.config;

import com.spunit.apidocs.filter.ApiDeprecationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for API Deprecation Management
 */
@Configuration
public class ApiDeprecationConfig {

    @Bean
    public FilterRegistrationBean<ApiDeprecationFilter> apiDeprecationFilter() {
        FilterRegistrationBean<ApiDeprecationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ApiDeprecationFilter());
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}

