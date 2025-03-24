package com.crm.evaluation.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CustomSessionFilter> sessionFilter() {
        FilterRegistrationBean<CustomSessionFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CustomSessionFilter());
        registrationBean.addUrlPatterns("/*"); // Filtrer toutes les requêtes
        return registrationBean;
    }
}
