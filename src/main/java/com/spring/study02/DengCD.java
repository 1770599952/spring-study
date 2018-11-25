package com.spring.study02;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary //处理歧义的第一种方式
public class DengCD implements CD{

	public void sing() {
		System.out.println("泡沫");
		System.out.println("再见");
	}

}
