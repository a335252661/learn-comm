package cn.cld.controller;

import cn.cld.service.OracleLocalhostTestService;
import cn.cld.service.OracleTestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2020/9/16
 */
@RestController
public class DataSourceTestController {
    @Resource
    private OracleLocalhostTestService oracleLocalhostTestService;
    @RequestMapping("/datasource")
    public String fun() {
        String val = oracleLocalhostTestService.fun1();
        return val;
    }
}
