package com.spring.study08;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@ComponentScan
public class BaseConfiguration {

    @Bean(name = "dataSource")
    public ComboPooledDataSource getDataSource() {
        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/dbgirl");
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setUser("root");
            dataSource.setPassword("root");
            dataSource.setMaxPoolSize(75);
            return dataSource;
        } catch (Exception e) {
            return null;
        }
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }
}
