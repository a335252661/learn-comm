package cn.cld.learnspshirok.controller;

import cn.cld.learnspshirok.entity.MyUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/2/22
 */
@Controller
public class LoginThymeleafController {

    @RequestMapping({"/","/index"})
    public String fun(Model model) {
        model.addAttribute("msg","hello,shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String fun2() {
        return "/user/add";
    }
    @RequestMapping("/user/update")
    public String fun3() {
        return "/user/update";
    }

    @RequestMapping("/toLogin")
    public String fun4() {
        return "/login";
    }

    //<form th:action="@{/usr/login}" method="post">
    @RequestMapping("/usr/login")
    public String fun5(String user , String pwd , Model model) {
        System.out.println("表单提交 ， 执行登陆-----");
        System.out.println("username-----"+user);
        System.out.println("password-----"+pwd);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user, pwd);

        try {
            //使用subject登陆
            System.out.println("使用subject登陆,开始");
            subject.login(usernamePasswordToken);
            System.out.println("使用subject登陆,结束");

            MyUser user1 = new MyUser();
            user1.setName(user);
            //登陆成功 存入session
            Session session = subject.getSession();
            session.setAttribute("loginUser" , user1);


        } catch (UnknownAccountException e) {
            System.out.println("用户不存在");
            model.addAttribute("msg","用户不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
            model.addAttribute("msg","密码错误");
            return "login";
        }
//        return "/index";   //  index  和 /index  都是直接返回页面
        return "redirect:/index";   //  index  和 /index  都是直接返回页面  redirect:/index 重定向到 index 接口
    }

    @GetMapping("Unauthorized")
    @ResponseBody
    public String fun6() {
        return "未授权页面，无法访问！！";
    }

}
