package com.spring.study03;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration	// 该类是一个配置文件类
@ComponentScan	// 默认扫描与配置类相同的包。
// 启动切面，proxyTargetClass： true使用CGLIB动态代理,false使用JDK动态代理。exposeProxy：true:将代理对象暴露在当前线程中。
@EnableAspectJAutoProxy(proxyTargetClass = true,exposeProxy = true)
@EnableTransactionManagement
public class PersonConfig {

}
