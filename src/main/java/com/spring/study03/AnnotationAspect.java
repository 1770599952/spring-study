package com.spring.study03;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect	// 声名该类是一个切面，
@Component
public class AnnotationAspect {

	// 声明切点：切点定义切面何处被使用
	@Pointcut("execution(* *.action(*))")
    public void action() {
    }
 
    @Pointcut("execution(* *.work(*))")
    public void work() {
    }
 
    @Pointcut("action() || work())")
    public void compositePointcut() {
    	
    }
 
    // 通知定义切面何时被使用
    //前置通知
    @Before("compositePointcut()")
    public void beforeAdvice() {
        System.out.println("before advice.................");
    }
 
    //后置通知
    @After("compositePointcut()")
    public void doAfter() {
        System.out.println("after advice..................");
    }
}
