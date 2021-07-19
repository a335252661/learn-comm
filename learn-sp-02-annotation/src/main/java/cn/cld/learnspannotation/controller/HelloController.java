package cn.cld.learnspannotation.controller;

import cn.cld.learnspannotation.annlearn.ConfigurationPropertiesLearn;
import cn.cld.learnspannotation.annotation.AppAuthenticationValidate;
import cn.cld.learnspannotation.bean.Car;
import cn.cld.learnspannotation.service.Fruit;
import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/5/7
 */
@RestController
public class HelloController {

    @Autowired
    public ConfigurationPropertiesLearn configurationPropertiesLearn;

    @Resource(name = "addCarsome") //这样拿到的就是配置文件里面提供的bean addCarsome
    private Car cc;

    @Autowired
    private Car addCarsome; //这样拿到的也是配置文件里面提供的bean addCarsome

    //Field fruit in cn.cld.learnspannotation.controller.HelloController required a single bean, but 2 were found:
    //方案1  @Qualifier("apple")
    // 方案2  primary
    //可以在实现类上使用primary ， 多个候选者且无法决定使用哪一个时，优先使用带有该注解的bean.
//    @Autowired
//    Fruit fruit; //frute接口，有两个实现类，直接注入，不知道使用哪个实现类，会报错
    //
    @Autowired
//    @Qualifier("apple")
    Fruit fruit;


    @Autowired
    Fruit orangeTest01;

    //cld:
    //  getTest: ${testconsul}
    //  getTest2: '嘻嘻哈哈'
    @Value("${cld.getTest:}")
    private String getTest;
    @Value("${cld.getTest2:}")
    private String getTest2;

    @RequestMapping("/hello")
    public String fun() {
        return "hello";
    }

    @AppAuthenticationValidate(requestParams={"嘻嘻" ,"admin"})
//    @AppAuthenticationValidate(value = "xixi")
    @RequestMapping("/hello2")
    public String fun2() {
        return "hello2";
    }

    @RequestMapping("/testadvice")
    public String fun3() {
        String xx = null;
        System.out.println(xx.replaceAll("xx" , "2"));
        return "xx";
    }

    @RequestMapping("/testadviceAddParam")
    public String fun4(Model model) {
        return model.getAttribute("globalAttr").toString();
    }

    @RequestMapping("/testConfigurationProperties")
    public String fun5() {
        return configurationPropertiesLearn.host;
    }

    @RequestMapping("/car")
    public String fun6() {
        return cc.getName()+addCarsome.getName();
    }

    @RequestMapping("/consulTest")
    public String fun7() {
        return getTest+getTest2;
    }

    @RequestMapping("/fruit")
    public String fun8() {
        fruit.color();
        return "fruit.color()";
    }

    @RequestMapping("/fruit2")
    public String fun9() {
        fruit.color();
        return "fruit.color()";
    }

}
