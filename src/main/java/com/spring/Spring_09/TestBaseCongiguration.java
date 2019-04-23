package com.spring.Spring_09;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring 与 内部Bean如何建立联系。
 */
public class TestBaseCongiguration {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BaseConfiguration.class);
        User user =  applicationContext.getBean(User.class);
        System.out.println(user.name);

    }
}
