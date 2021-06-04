package cn.cld.learnspannotation.controller;

import cn.cld.learnspannotation.annotation.AppAuthenticationValidate;
import cn.hutool.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/5/7
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String fun() {
        return "hello";
    }
    @AppAuthenticationValidate(requestParams={"嘻嘻" ,"admin"})
    @RequestMapping("/hello2")
    public String fun2(JSONObject jsonObject) {
        return "hello2";
    }
}
