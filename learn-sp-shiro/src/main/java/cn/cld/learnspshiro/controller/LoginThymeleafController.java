package cn.cld.learnspshiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/2/22
 */
@Controller
@RequestMapping("thy")
public class LoginThymeleafController {

    @RequestMapping("login")
    public String fun() {
        return "index";
    }

}
