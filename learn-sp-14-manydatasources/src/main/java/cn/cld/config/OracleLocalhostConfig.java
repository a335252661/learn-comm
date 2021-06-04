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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

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
@MapperScan(basePackages = "cn.cld.mapper.oracle_localhost" , sqlSessionFactoryRef = "oracleLocalhostSqlSessionFactory")
public class OracleLocalhostConfig implements ResourceLoaderAware {

    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;

//    @Value("${mybatis.config-location}")
//    private String configLocation;

    private ResourceLoader resourceLoader;

    @Primary
    @Bean(name="oracleLocalhostDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.oraclelocal.druid")
    public DataSource oracleLocalhostDataSource() {
        log.info("------Build localhost Datasource-------");
        return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();
    }


    @Primary
    @Bean(name = "oracleLocalhostSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        log.info("------Build localhost SqlSessionFactory-------");
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        if (StringUtils.hasText(configLocation)) {
//            sessionFactory .setConfigLocation(resourceLoader.getResource(configLocation));
//        }
        sessionFactory.setDataSource(oracleLocalhostDataSource());
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        return sessionFactory.getObject();
    }


    @Primary
    @Bean("oracleLocalhostTransactionManager")
    public DataSourceTransactionManager transactionManager(
            @Qualifier("oracleLocalhostDataSource") DataSource oracleCopyDataSource) {
        log.info("------Build localhost TransactionManager-------");
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(oracleCopyDataSource);
        return dataSourceTransactionManager;
    }

    @Primary
    @Bean(name = "oracleLocalhostJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("oracleLocalhostDataSource") DataSource dataSource) {
        log.info("------Build localhost jdbcTemplate-------:" + dataSource);
        return new JdbcTemplate(dataSource);
    }


    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
