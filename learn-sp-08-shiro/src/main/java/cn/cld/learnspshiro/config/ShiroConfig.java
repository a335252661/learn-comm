package cn.cld.learnspshiro.config;

import cn.cld.learnspshiro.shiro.cache.RedisCacheManager;
import cn.cld.learnspshiro.shiro.realms.MyMD5realm;
import cn.cld.learnspshiro.shiro.realms.Myrealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
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


    //  jsp  时候 用这个
    //负责拦截所有请求
//    @Bean
//    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        //给filter设置安全管理器
//        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
//
//        //配置系统的受限资源
//        //配置系统的公共资源
//
//        HashMap<String, String> map = new HashMap<String, String>();
//
//        map.put("/user/login" , "anon");  //表示我们的/user/login 登陆资源不需要验证
//        map.put("/user/register" , "anon");  //表示我们的/user/login 登陆资源不需要验证
//        map.put("/user/**" , "anon");  //表示我们的/user/login 登陆资源不需要验证
//        map.put("/register.jsp" , "anon");  //表示我们的/user/login 登陆资源不需要验证
//
////        map.put("/index.jsp" , "authc");  //authc 表示请求这个资源需要认证和授权   anon 表示此资源不需要经过认证
//        map.put("/thy/**" , "anon");  //表示我们的/user/login 登陆资源不需要验证
//
//
//        map.put("/**" , "authc");  //表示所有的资源都需要经过认证
//
//        //当我访问  http://localhost:6060/shiro/index.jsp   的时候，会自动跳转到 http://localhost:6060/shiro/login.jsp
//        //默认的认证界面的路径  /login.jsp
//
//        //自定义认证路径
//        shiroFilterFactoryBean.setLoginUrl("/mylogin.jsp");
//
//
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
//
//        return shiroFilterFactoryBean;
//    }

    //  thymeleaf  时候 用这个
    //负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //配置系统的受限资源
        //配置系统的公共资源

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("/user/login" , "anon");  //表示我们的/user/login 登陆资源不需要验证
        map.put("/user/register" , "anon");  //表示我们的/user/login 登陆资源不需要验证
        map.put("/user/**" , "anon");  //表示我们的/user/login 登陆资源不需要验证
        map.put("/register.jsp" , "anon");  //表示我们的/user/login 登陆资源不需要验证

//        map.put("/index.jsp" , "authc");  //authc 表示请求这个资源需要认证和授权   anon 表示此资源不需要经过认证
        map.put("/thy/**" , "anon");  //表示我们的/user/login 登陆资源不需要验证


        map.put("/**" , "authc");  //表示所有的资源都需要经过认证

        //当我访问  http://localhost:6060/shiro/index.jsp   的时候，会自动跳转到 http://localhost:6060/shiro/login.jsp
        //默认的认证界面的路径  /login.jsp

        //自定义认证路径
        shiroFilterFactoryBean.setLoginUrl("/mylogin.jsp");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    //常见安全管理器带web功能的
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager(realm);
        return defaultWebSecurityManager;
    }

    //创建自定义的realm
    @Bean
    public Realm getRealm() {
        MyMD5realm myMD5realm = new MyMD5realm();

        //修改凭证校验匹配器
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1024);

        myMD5realm.setCredentialsMatcher(hashedCredentialsMatcher);

        //开启缓存管理
//        myMD5realm.setCacheManager(new EhCacheManager());

        //使用自定义的redis来实现缓存
        myMD5realm.setCacheManager(new RedisCacheManager());

        myMD5realm.setCachingEnabled(true);//开启全局缓存
        myMD5realm.setAuthenticationCachingEnabled(true); //身份认证缓存
        myMD5realm.setAuthenticationCacheName("AuthenticationCache"); //给身份认证缓存在内存中起一个名字

        myMD5realm.setAuthorizationCachingEnabled(true); //开始授权缓存
        myMD5realm.setAuthorizationCacheName("AuthorizationCache");//给授权缓存在内存中起一个名字

        return myMD5realm;
    }

}
