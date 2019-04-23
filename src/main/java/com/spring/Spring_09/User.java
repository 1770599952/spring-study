package com.spring.Spring_09;

import org.springframework.beans.factory.BeanNameAware;

public class User implements BeanNameAware{

    String name;

    @Override
    public void setBeanName(String s) {
        this.name = s;
        System.out.println("name" + s);
    }
}
