package com.spring.Spring_03;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//编写实现类
@Service
public class PersonServiceImpl implements IPersonService {

	@Transactional
	public String action(String msg) {
		System.out.println("FooService, method doing.");
		System.out.println("action:"+this);
		this.work(msg); 		// *** 代码 1 ***
//		IPersonService service = ((IPersonService) AopContext.currentProxy());	// *** 代码 2 ***
//		service.work(msg);
//		System.out.println("this:"+this.getClass().getSimpleName());

		return "[" + msg + "]";
	}

	@Override
	@Transactional
	public String work(String msg) {
		System.out.println("work"+this);
		System.out.println("work: * " + msg + " *");
		return "* " + msg + " *";
	}
}
