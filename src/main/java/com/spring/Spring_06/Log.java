package com.spring.Spring_06;

import java.io.PrintStream;

public class Log {

	private PrintStream stream;

	public Log() {
		super();
	}

	public Log(PrintStream stream) {
		this.stream = stream;
	}
	
	public void recordLogBefore() {
		System.out.println("记录日志:骑士要开始探险了！");
	}
	
	public void recordLogAfter() {
		System.out.println("记录日志:其实探险完成了！");
	}

	
}
