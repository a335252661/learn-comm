package cn.cld.learnspshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/2/21
 */
@Controller
@RequestMapping("order")
public class OrderController {

    @RequestMapping("save")
    @ResponseBody
    public String save() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("admin")) {
            System.out.println("保存订单成功！");
            return "保存订单成功！";
        } else {
            System.out.println("保存失败");
            return "保存失败";
        }
    }

    @RequestMapping("save2")
    @RequiresRoles("admin")
//    @RequiresRoles(value={"admin" , "user"})   //表示同时具有两种角色
//    @RequiresPermissions("user:delete:02") //用来判断权限字符串的
    @ResponseBody
    public String save2() {
        System.out.println("保存订单成功！");
        return "保存订单成功！";
    }

}
