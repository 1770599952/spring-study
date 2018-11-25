package com.spring.study04;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//单元测试
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:study04/applicationContext.xml" })
public class FooServiceTest {

	@Autowired
	private IPersonService personService;

	@Test
	public void testAction() {
		System.out.println(personService.getClass().getSimpleName());
		personService.action("hello world.");
	}
	
	@Test 
	public void batchUser() {
		personService.batchInsertUser();
	}

}
