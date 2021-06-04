package cn.cld.learnspshirok.config;

//import cn.cld.learnspshiro.shiro.cache.RedisCacheManager;
//import cn.cld.learnspshiro.shiro.realms.MyMD5realm;
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import cn.cld.learnspshirok.shiro.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/2/7
 */
@Configuration
public class ShiroConfig {


    // 用户在html中在使用shiro标签
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

    // 在shiro中配置 reaml
//    @Bean
    @Bean(name = "xxxReaml")
    public Realm fun() {
        return new UserRealm();
    }
    // 在shiro中配置 reaml
    @Bean
    public Realm getReal() {
        return new UserRealm();
    }

    @Bean
//    public DefaultWebSecurityManager fun2(Realm realm) {
//    public DefaultWebSecurityManager fun2(@Qualifier("fun") Realm realm) { //如果在shiro中注册了多个 realm ，就需要指定具体使用哪个
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("xxxReaml") Realm realm) { //如果在shiro中注册了多个 realm ，就需要指定具体使用哪个
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager(realm);
        return defaultWebSecurityManager;
    }

    //对页面进行拦截
    //给页面添加xx权限
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        // anon  登陆资源不需要验证
        // authc  登陆资源需要验证
        // perms  验证必须有什么权限才可以访问
        // user  必须拥有记住我功能才能使用
        // role  必须拥有角色权限才能访问
        //配置系统的受限资源
        //配置系统的公共资源
        //    @RequestMapping("/user/add")
        //    public String fun2() {
        //        return "/user/add";
        //    }
        //    @RequestMapping("/user/update")
        //    public String fun3() {
        //        return "/user/update";
        //    }
        HashMap<String, String> map = new HashMap<String, String>();
        // 如果登陆的用户名优这个权限 user:add 就会报错There was an unexpected error (type=Unauthorized, status=401).
        map.put("/user/add" , "perms[user:add]");
        map.put("/user/update" , "authc");
//        map.put("/register.jsp" , "anon");  //表示我们的/user/login 登陆资源不需要验证
////        map.put("/index.jsp" , "authc");  //authc 表示请求这个资源需要认证和授权   anon 表示此资源不需要经过认证
//        map.put("/thy/**" , "anon");  //表示我们的/user/login 登陆资源不需要验证
//        map.put("/**" , "authc");  //表示所有的资源都需要经过认证
//        //当我访问  http://localhost:6060/shiro/index.jsp   的时候，会自动跳转到 http://localhost:6060/shiro/login.jsp
//        //默认的认证界面的路径  /login.jsp
//        //自定义认证路径
//        shiroFilterFactoryBean.setLoginUrl("/mylogin.jsp");
        //通过toLogin接口跳转到login页面，提交表单，到我们的@RequestMapping("/usr/login") 这个接口
        // realm里面的认证方法doGetAuthenticationInfo会进行验证拦截
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/Unauthorized");  //设置未授权页面跳转请求
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

}
