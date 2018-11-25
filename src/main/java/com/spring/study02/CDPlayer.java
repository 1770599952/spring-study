package com.spring.study02;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class CDPlayer implements BeanNameAware,ApplicationContextAware {

	@Autowired
	// @Qualifier("linCD") 进一步的解决方案。
	// 如果没有指定其他的限定符的话，所有的bean都会给定一个默认的限定符，这个限定符与bean的ID相同。
	
	//@SongNamer
	//@MusicNamer
	private CD cd;
	
	private String brand;	
	private String title;

	private String id;

	private ApplicationContext applicationContext;


	public CDPlayer() {
		super();
	}

	public CDPlayer(String brand, String title) {
		super();
		this.brand = brand;
		this.title = title;
	}

	public CD getCd() {
		return cd;
	}

	public void setCd(CD cd) {
		this.cd = cd;
	}
	
	public void singCd() {
		cd.sing();
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	@Override
	public void setBeanName(String s) {
		this.id = s;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
