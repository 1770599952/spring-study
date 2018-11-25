package com.spring.study04;

import java.util.ArrayList;
import java.util.List;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.study04.bean.User;
import com.spring.study04.dao.UserDao;

@Service
@Transactional(propagation = Propagation.REQUIRED, timeout = 10000000)
@Primary
public class PersonServiceImpl2 implements IPersonService {
 
    @Autowired
    private UserDao userDAO;
 
    @Override
    public String action(final String msg) {
 
        User user = new User();
        user.setName("lanlan");
        user.setPassword("11");
        userDAO.insert(user);
        work("insert");
        return "success";
    }
 
    @Override
    public String work(String msg) {
        System.out.println("this" + this.getClass().getSimpleName()+ "work: * " + msg + " *");
        User userDO = new User();
        userDO.setName("yanyan");
        userDO.setPassword("11");
        userDAO.insert(userDO);
        return "";
    }
    // 结果：work方法中抛出异常，但是没有影响事务的回滚，说明事务在子线程中失效了。
    // 解决方案 只需要将多线程中的方法提出来，或者作为另一个Service类中的方法即可
    
    //上面只是一个简单的例子，用于进行问题说明。
	//	
	//	a、如果去掉多线程，将方法放在同一个类里，Spring则会根据事务的传播配置参数，是否重新启用新的事务。
	//	
	//	b、如果将方法独立出来放在新的类里，并且该方法也配置了事务，则会重新启用新的事务。
    
    // 原因分析
	//    Spring的事务处理为了与数据访问解耦，它提供了一套处理数据资源的机制，而这个机制与上文中的原理相差无几，也是采用的ThreadLocal的方式。
	//
	//    在编程中，Service实例都是单例的无状态的，事务管理则需要加入事务控制的相关状态变量，使得Service实例不再是无状态线程安全的，解决这个问题的方式就是使用ThreadLocal。
	//
	//    通过使用ThreadLocal将数据源绑定在当前线程上，在当前线程的事务中，从设定的地方去取连接就会是同一个数据库连接，这样操作事务就会在同一个连接上进行。
	//
	//    如下图所示：
	//
	//
	//
	//    但是，ThreadLocal的特性是，绑定在当前线程中的变量不会自动传递到其它线程中（当然，InheritableThreadLocal可以在父子线程中间传递变量值，但是这需要特殊的使用场景），所以当开启子线程时，子线程并没有父线程的数据库连接资源。
	//
	//    对于上文提到的陷阱：如果另外开启线程，那么在新线程中将获取不到父线程的连接，事务要么失效，要么重新开启一个新的。
    
    @SuppressWarnings("all")
    private IPersonService getThis() {
        try {
            return (IPersonService) AopContext.currentProxy();
        } catch (IllegalStateException e) {
            return this;
        }
    }

	@Override
	public void batchInsertUser() {
        User user1 = new User();
        user1.setName("111");
        user1.setPassword("11");
        
        User user2 = new User();
        user2.setName("22");
        user2.setPassword("22"); 
        List<User> userList = new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);     
        
        userDAO.insertBatch(userList);
   //     userDAO.insert(user1);
	}
}