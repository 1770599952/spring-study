package com.spring.Spring_02;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CDPlayerConfig.class)
public class TestCD {
	
	@Autowired
	private CDPlayer player;
	
	//自动装配歧义性
	// 警告: Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'CDPlayer': Unsatisfied dependency expressed through field 'cd'; nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.spring.Spring_02.CD' available: expected single matching bean but found 2: dengCD,linCD
	@Test
	public void play(){
		player.singCd();
	}
	
}
