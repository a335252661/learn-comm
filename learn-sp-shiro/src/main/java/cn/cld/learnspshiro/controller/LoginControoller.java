package cn.cld.learnspshiro.controller;

import cn.cld.learnspshiro.dao.UserDAO;
import cn.cld.learnspshiro.entity.User;
import cn.cld.learnspshiro.service.UserService;
import cn.cld.learnspshiro.utils.VerifyCodeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/2/7
 */
@Controller
@RequestMapping("user")
public class LoginControoller {

    @Resource
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping("register")
    public void register(User user) {
        int id = userService.register(user);
        System.out.println("-----------------------------------------------------" + id);
    }


    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/mylogin.jsp";
    }


    @RequestMapping("login")
    public String login(HttpSession session , String username , String password , String code) {

        //比较验证码
        String code1 = session.getAttribute("code").toString();

        if(code1.equalsIgnoreCase(code)){

            //获取主体对象
            Subject subject = SecurityUtils.getSubject();

            //将用户名和密码封装成登陆的token    ,这里是用的页面上输入的用户名  ， 和密码
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                //登陆
                // 此时调用  myMD5realm 的 doGetAuthenticationInfo
                subject.login(token);
                return "redirect:/index.jsp";
            } catch (UnknownAccountException e) {
                System.out.println("用户不存在");
            } catch (IncorrectCredentialsException e) {
                System.out.println("密码错误");
            }


        }else {

            throw  new RuntimeException("验证码错误！");

        }
//        异常
        return "redirect:/mylogin.jsp";
    }


    @RequestMapping("getImage")
    public void getImage(HttpSession session , HttpServletResponse response) throws  Exception {
        //生成验证码
        String code = VerifyCodeUtils.generateVerifyCode(4);
        //生成的时候就把正确的验证码放入session ， 然后用户登陆的时候获取输入的 和在session中获取正确的作比较
        session.setAttribute("code" , code);
        //验证码存入图片
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("image/png");

        VerifyCodeUtils.outputImage(220 , 60 , outputStream , code);
    }

}
