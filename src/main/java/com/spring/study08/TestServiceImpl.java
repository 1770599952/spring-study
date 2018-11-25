package com.spring.study08;

import org.springframework.aop.framework.AopContext;
import org.springframework.aop.framework.AopProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Scanner;

@Service("testServiceImpl")
public class TestServiceImpl implements TestService {

    @Autowired
    private JdbcTemplate dao;

    @Autowired
    private DataSource dataSource;

    @Override
    @Transactional
    public void action() throws Exception {

        Connection connection = dao.getDataSource().getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO sys_user(name,password,ch_name,group_id) VALUES(?,?,?,?)";
        int count = dao.update(sql, "testa", "testa", "testa", 1);
        System.out.println("action:" + this);
        // work();
        TestService service = (TestService) AopContext.currentProxy();
        service.work();
        System.out.println("AOPSERVICE:" + service.getClass().getSimpleName());
    }

    @Override
    @Transactional
    public void work() {
        String sql = "INSERT INTO sys_user(name,password,ch_name,group_id) VALUES(?,?,?,?)";
        int count = dao.update(sql, "test", "test", "test", 1);
        System.out.println("work:" + this);
        //	throw new RuntimeException("故意抛出异常");
    }

    @Override
    public void testDatasourceExistArea() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);

        dataSource = null;

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(dataSource == null);
                new Scanner(System.in).next();
            }
        }).start();

    }

}
