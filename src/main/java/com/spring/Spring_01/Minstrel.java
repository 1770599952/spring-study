package com.spring.Spring_01;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;

import java.io.PrintStream;


public class Minstrel implements DisposableBean,BeanNameAware {

	private PrintStream stream;

	private String name;

	public Minstrel() {
		super();
	}

	public Minstrel(PrintStream stream) {
		this.stream = stream;
	}

	public void singBeforeQuest() {
		stream.println("Fa la la, the knight is so brave!");
	}

	public void singAfterQuest() {
		stream.println("Tee hee hee, the brave knight " + "did embark on a quest!");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("1111");
	}

	@Override
	public void setBeanName(String name) {
		this.name = name;
	}
}
