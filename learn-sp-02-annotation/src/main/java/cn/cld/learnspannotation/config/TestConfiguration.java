package cn.cld.learnspannotation.config;

import cn.cld.learnspannotation.interceptor.CarInterceptor;
import cn.cld.learnspannotation.interceptor.TestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/18
 */
@Configuration
public class TestConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 将创建的拦截器注册进来
        registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**");//表示所有的请求都拦截
        registry.addInterceptor(new CarInterceptor()).addPathPatterns("/car");
    }
}
