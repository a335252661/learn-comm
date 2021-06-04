package cn.cld.learnspjwt.config;

import cn.cld.learnspjwt.interceptor.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/4/28
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    JWTInterceptor jwtInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**") //表示拦截的资源
                .excludePathPatterns("/user/**"); //表示放行资源
    }
}
