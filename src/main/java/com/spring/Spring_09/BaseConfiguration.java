package com.spring.Spring_09;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseConfiguration {

    @Bean
    public BeanPostProcessor beanPostProcessor() {
        return new SpringBeanPostProcessor();
    }

    @Bean
    public Object user() {
        return new User();
    }
}
