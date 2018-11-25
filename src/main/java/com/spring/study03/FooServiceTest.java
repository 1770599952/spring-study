package com.spring.study03;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//单元测试
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:study03/applicationContext.xml"})
public class FooServiceTest {

  @Autowired
  private IPersonService personService;

  @Test
  public void testAction() {
	  System.out.println(personService.getClass().getSimpleName());
      personService.action("hello world.");
  }
  
  // 测试结果：
  //	说明嵌套在action方法内部的work方法没有被进行切面增强，它没有被“切中”。
  
  /*  解决方案
	  	在实现类中，如果注释掉代码1，将代码1改为：
	    ((IPersonService) AopContext.currentProxy()).work(msg);   // *** 代码 2 ***
	           并且在XML配置中加上expose-proxy="true"，变为：<aop:aspectj-autoproxy expose-proxy="true"/>
	
	  运行结果为：
		before advice.................
		FooService, method doing.
		before advice.................
		work: * hello world. *
		after advice..................
		after advice..................	
	
	  嵌套在action方法内部的work方法被进行了切面增强，它被“切中”。*/
  
  /*
   * 	原理分析:
   * 	以上结果的出现与Spring AOP的实现原理息息相关，由于Spring AOP采用了动态代理实现AOP，在Spring容器中的bean（也就是目标对象）会被代理对象代替，
   * 	代理对象里加入了我们需要的增强逻辑，当调用代理对象的方法时，目标对象的方法就会被拦截。而上文中问题出现的症结也就是在这里，为了进一步说明这个问题，用图片说明最好：
   *	
   *	通过调用代理对象的action方法，在其内部会经过切面增强，然后方法被发射到目标对象，在目标对象上执行原有逻辑，如果在原有逻辑中嵌套调用了work方法，则此时work方法并没有被进行切面增强，因为此时它已经在目标对象内部。
		而解决方案很好地说明了，将嵌套方法发射到代理对象，这样就完成了切面增强。
   * 
   * */
  
  /*  		源代码分析:

  			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
  			      ... ...
  			 
  			      Object retVal;
  			 
  			      //*** 代码3 ***
  			      if (this.advised.exposeProxy) {
  			         // Make invocation available if necessary.
  			         oldProxy = AopContext.setCurrentProxy(proxy);
  			         setProxyContext = true;
  			      }
  			 
  			      ... ...
  			 
  			      // Get the interception chain for this method.
  			      List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
  			 
  			      // Check whether we have any advice. If we don't, we can fallback on direct
  			      // reflective invocation of the target, and avoid creating a MethodInvocation.
  			      if (chain.isEmpty()) {
  			         // We can skip creating a MethodInvocation: just invoke the target directly
  			         // Note that the final invoker must be an InvokerInterceptor so we know it does
  			         // nothing but a reflective operation on the target, and no hot swapping or fancy proxying.
  			         retVal = AopUtils.invokeJoinpointUsingReflection(target, method, args);
  			      }
  			      else {
  			         // We need to create a method invocation...
  			         invocation = new ReflectiveMethodInvocation(proxy, target, method, args, targetClass, chain);
  			         // Proceed to the joinpoint through the interceptor chain.
  			         retVal = invocation.proceed();
  			      }
  			 
  			      ... ...
  			}
  		在代码3处，如果配置了exposeProxy开关，则会将代理对象暴露在当前线程中，以供其它需要的地方使用。那么是怎么暴露的呢？答案很简单，通过使用静态的全局ThreadLocal变量就解决了问题。
  	*/
  
  
}
