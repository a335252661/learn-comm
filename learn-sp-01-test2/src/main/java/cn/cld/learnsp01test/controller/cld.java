package cn.cld.learnsp01test.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;

import java.util.Date;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/7/6
 */
public class cld {
    public static void main(String[] args) {
        // 1625557748
        System.out.println(new Date().getTime()/1000);

        String result1= HttpUtil.get("https://www.baidu.com");


//
//        HttpRequest.get("http://wysun.cn/ebus/learn_sp/hello/")
//                .header("")

        System.out.println(result1);

    }
}
