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
@MapperScan(basePackages = "com.xchen.airecieveandforward.dao.sys", sqlSessionFactoryRef = "sysSqlSessionFactory")
public class SysDataSourceConfig {
    @Value("${datasource.sys.driver-class-name}")
    private String driverClassName;
    @Value("${datasource.sys.jdbc-url}")
    private String url;
    @Value("${datasource.sys.username}")
    private String username;
    @Value("${datasource.sys.password}")
    private String password;

    @Bean(name = "sysDataSource")
    public DataSource sysDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setMaximumPoolSize(10);
        dataSource.setMinimumIdle(5);
        dataSource.setPoolName("sysDataSourcePool");
        return dataSource;
    }
    @Bean(name = "sysSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("sysDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mappers/sys/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "sysTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("sysDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean(name = "sysSqlSessionTemplate")
    public SqlSessionTemplate sysSqlSessionTemplate(@Qualifier("sysSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
