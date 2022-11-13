package com.xchen.airecieveandforward.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author 28609
 */
@Configuration
@MapperScan(basePackages = "com.xchen.airecieveandforward.dao.supervision", sqlSessionFactoryRef = "supervisionSqlSessionFactory")
public class SupervisionDataSourceConfig {
    @Value("${datasource.supervision.driver-class-name}")
    private String driverClassName;
    @Value("${datasource.supervision.jdbc-url}")
    private String url;
    @Value("${datasource.supervision.username}")
    private String username;
    @Value("${datasource.supervision.password}")
    private String password;

    @Bean(name = "supervisionDataSource")
    public DataSource supervisionDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setMaximumPoolSize(10);
        dataSource.setMinimumIdle(5);
        dataSource.setPoolName("supervisionDataSourcePool");
        return dataSource;
    }
    @Bean(name = "supervisionSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("supervisionDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mappers/supervision/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "supervisionTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("supervisionDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean(name = "supervisionSqlSessionTemplate")
    public SqlSessionTemplate supervisionSqlSessionTemplate(@Qualifier("supervisionSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
