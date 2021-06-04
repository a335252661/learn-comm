package cn.cld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2020/9/15
 */
@Controller //注：RestController和Controller注解的区别是：RestController是返回的内容就是返回的内容，相当于加个@ResponseBody,而controller一般是返回的页面
public class LoginController {
    @RequestMapping("/login")
    public String login() {
        return "index";
    }
}
