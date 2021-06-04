package cn.cld.config;

import lombok.extern.java.Log;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2020/9/16
 */
@Log
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "cn.cld.mapper.mysql_localhost" , sqlSessionFactoryRef = "mysqlLocalhostSqlSessionFactory")
public class MysqlLocalhostConfig {

    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;

//    @Value("${mybatis.config-location}")
//    private String configLocation;

    private ResourceLoader resourceLoader;

    @Bean(name="mysqlLocalhostDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysqllocal.druid")  //复制时候注意修改前缀
    public DataSource mysqlLocalhostDataSource() {
        log.info("------Build mysql localhost Datasource-------");
        return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();
    }


    @Bean(name = "mysqlLocalhostSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        log.info("------Build mysql localhost SqlSessionFactory-------");
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        if (StringUtils.hasText(configLocation)) {
//            sessionFactory .setConfigLocation(resourceLoader.getResource(configLocation));
//        }
        sessionFactory.setDataSource(mysqlLocalhostDataSource());
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        return sessionFactory.getObject();
    }


    @Bean("mysqlLocalhostTransactionManager")
    public DataSourceTransactionManager transactionManager(
            @Qualifier("mysqlLocalhostDataSource") DataSource mysqlCopyDataSource) {
        log.info("------Build mysql localhost TransactionManager-------");
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(mysqlCopyDataSource);
        return dataSourceTransactionManager;
    }

    @Bean(name = "mysqlLocalhostJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("mysqlLocalhostDataSource") DataSource dataSource) {
        log.info("------Build mysql localhost jdbcTemplate-------:" + dataSource);
        return new JdbcTemplate(dataSource);
    }

}
