package cld.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/4/19
 */
@RestController
public class HelloController {

    @RequestMapping("hello")
    public String fun() {
        return "hello Docker";
    }

}
