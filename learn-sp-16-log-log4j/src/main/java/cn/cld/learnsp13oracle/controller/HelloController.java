package cn.cld.learnsp13oracle.controller;

import cn.cld.learnsp13oracle.service.QuerySome;
import cn.hutool.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/1
 */
@RestController
public class HelloController {

    @Autowired
    private  QuerySome querySome;

    @RequestMapping("hello")
    public List<HashMap> fun() {
        List<HashMap> js = querySome.queryData();
        return js;
    }
}
