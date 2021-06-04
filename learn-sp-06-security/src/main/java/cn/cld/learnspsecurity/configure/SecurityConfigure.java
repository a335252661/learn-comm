package cn.cld.learnspsecurity.configure;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/4/28
 */
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表示 / /level2/** /level3/**  permitAll  都不会拦截，所有人都可以访问
        // 请求到 /level1/** 需要有 vip1 权限才能访问 ，没有权限返回报错
        // There was an unexpected error (type=Forbidden, status=403).
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasAnyRole("vip1")
                .antMatchers("/level2/**").hasAnyRole("vip2")
                .antMatchers("/level3/**").permitAll();

        //配置没有权限 默认跳转到登录页面  http://localhost:8011/login 弹出的这个登录页面是security内置的，不配置路径默认就转到这里
//        http.formLogin();

        //定制登录页  配置没有权限 默认跳转到我们自己定义的登录页面  toLogin
        // 我们的表单 <form th:action="@{/usr/login}" method="post">
        //  <input type="text" placeholder="Username" name="user">
        // <input type="password" name="pwd">
        http.formLogin().loginPage("/toLogin")
                //我们的controller 里面没有 /usr/login 接口 ，这个地方被我们的security托管了，security 拦截到这个登录，从表单里面获取信息
                .loginProcessingUrl("/usr/login")
                // 默认表单使用的 username	password ，但是我们的表单不一样，所以设置下参数，告诉security
                .usernameParameter("user")
                .passwordParameter("pwd");

        //开启注功能
//        http.logout();

        // 开启注销功能，注销成功跳转到那个路径
        http.logout().logoutSuccessUrl("/");
        // 关闭csrf功能
//        http.csrf().disable();

        // 开启记住我功能，cookie ，默认保存两周，存在浏览cookie里面
//        http.rememberMe();
        // 因为现在跳转到了我们自己的登陆页面，<input type="checkbox" name="myremeber">   记住我  所以需要把我们自己的表单名字传过去
        http.rememberMe().rememberMeParameter("myremeber");
    }

    //认证
    // 如果你是明文密码，登录就给你报错，说你密码不安全，报错500 ， 添加passwordEncoder 解决
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("cld").password("123").roles("vip1","vip2")
//                .and()
//                .withUser("root").password("root").roles("vip1","vip2","vip3");


        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("cld").password(new BCryptPasswordEncoder().encode("123")).roles("vip1","vip2")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("root")).roles("vip1","vip2","vip3");

    }
}
