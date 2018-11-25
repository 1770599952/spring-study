package com.spring.study08;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BaseConfiguration.class)
public class TestTransaction {

    @Autowired
    private TestService service;

    @Test
    public void testAction() throws Exception {
        System.out.println(service.getClass().getSimpleName());
        service.action();
    }

    @Test
    public void testDatasourceExistArea() throws Exception {
        service.testDatasourceExistArea();
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BaseConfiguration.class);
        TestService service = (TestService) applicationContext.getBean("testServiceImpl");
        service.testDatasourceExistArea();
    }
}
