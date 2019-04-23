package com.spring.Spring_02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:/com/spring/Spring_02/mood.properties")
public class ExpressiveConfig {

	@Autowired
	Environment env;
	
	@Bean
	public CDPlayer player() {
		return new CDPlayer(env.getProperty("brand"),env.getProperty("title"));
	}
	
}
