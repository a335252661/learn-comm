//package cn.cld.controller;
//
//import cn.cld.service.OracleTestService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * @author 程刘德
// * @version 1.0
// * @Description TODO
// * @date 2020/9/15
// */
//@RestController
//public class OracleTestController {
//
//    @Resource
//    private OracleTestService oracleTestService;
//
//    @RequestMapping("/oracle")
//    public String oracle(){
//        String value =  oracleTestService.query();
//
//        return value+"";
//    }
//}
