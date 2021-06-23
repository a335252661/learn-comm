package cn.cld.learnsp13oracle.controller;

import cn.cld.learnsp13oracle.service.QuerySome;
import cn.hutool.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

//    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HelloController.class);
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private  QuerySome querySome;

    @RequestMapping("hello")
    public List<HashMap> fun() {

        log.error("Something else is wrong here--------------------------------");

        List<HashMap> js = querySome.queryData();
        return js;
    }
}
