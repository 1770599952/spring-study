package com.spring.Spring_02;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {ExpressiveConfig.class,CDPlayerConfig.class})
public class TestExpression {
	
	@Autowired
	private CDPlayer player;
	
	// java配置，运行时值注入
	@Test
	public void mood01() {
		System.out.println(player.getBrand());
	}
	
	
}
