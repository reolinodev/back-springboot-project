package com.back.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * RDBMS와 mybatis 세팅
 */
@Configuration
@MapperScan(value = "com.back.admin.repository")
public class DataConfig {

    @Value("${db.type}")
    String dbType;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        String locationPattern = "classpath:repository/postgresql/*.xml";

        sessionFactory.setDataSource(dataSource);

        Resource confiigLocation = new PathMatchingResourcePatternResolver()
            .getResource("classpath:mybatis-config.xml");
        sessionFactory.setConfigLocation(confiigLocation);

        if ("mariadb".equals(dbType)) {
            locationPattern = "classpath:repository/mariadb/*.xml";
        }

        sessionFactory.setMapperLocations(
            new PathMatchingResourcePatternResolver().getResources(locationPattern));
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

}
