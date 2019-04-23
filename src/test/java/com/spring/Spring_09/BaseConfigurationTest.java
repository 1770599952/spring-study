package com.spring.Spring_09;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BaseConfiguration.class)
public class BaseConfigurationTest {

    @Test
    public void user() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BaseConfiguration.class);
        User user =  applicationContext.getBean(User.class);
        System.out.println(user.name);
    }
}