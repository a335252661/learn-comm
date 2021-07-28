package cn.cld.learnsp18start.config;

import cn.cld.learnsp18start.filter.CldFirstFilter;
import cn.cld.learnsp18start.filter.CldSecondFilter;
import cn.cld.learnsp18start.properties.CldDemoProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/7/28
 */
@Configuration
// //使使用 @ConfigurationProperties 注解的类生效。
// 如果@ConfigurationProperties是在第三方包中，那么@component是不能注入到容器的。只有@EnableConfigurationProperties才可以注入到容器。
@EnableConfigurationProperties(CldDemoProperties.class)
@ConditionalOnClass(CldDemoProperties.class)
@ConditionalOnProperty(prefix = "cld.demo", value = "enabled", matchIfMissing = true)
public class CldTestAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(CldTestAutoConfiguration.class);

    public CldTestAutoConfiguration(){
        System.out.println("自动配置类-------CldTestAutoConfiguration----构造方法");
    }


    @PostConstruct
    public void init() {
        logger.info("start init CldTestAutoConfiguration");
    }

    @Bean
    public CldFirstFilter cldFirstFilter() {
        return new CldFirstFilter();
    }
    @Bean
    public CldSecondFilter cldSecondFilter() {
        return new CldSecondFilter();
    }


    @Bean
    public CldTestConfiguration cldTestTemplate(CldDemoProperties cldDemoProperties) {
        System.out.println("------cldTestTemplate---------初始化");
        CldTestConfiguration cldTestConfiguration = new CldTestConfiguration();
        cldTestConfiguration.setAddress(cldDemoProperties.getAddress());
        cldTestConfiguration.setName(cldDemoProperties.getName());
        return cldTestConfiguration;
    }

}
