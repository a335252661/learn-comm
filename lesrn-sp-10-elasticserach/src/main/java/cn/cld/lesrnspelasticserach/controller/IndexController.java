package cn.cld.lesrnspelasticserach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/1/23
 */
@Controller
public class IndexController {

    @GetMapping({"/" , "index"})
    public String index() {
        return "index";
    }

}
