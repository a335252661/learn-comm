package cn.cld.learnspannotation.annlearn;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/10
 */
@ControllerAdvice
public class ControllerAdviceLearn {

    // 全局的异常处理,拦截空指针异常
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Object rpcExceptionHandler(Exception e){
        System.out.println("-----------------------------------------------------");
        System.out.println(e.getMessage());
        System.out.println("全局的异常处理,拦截空指针异常");
        return "拦截空指针异常";
    }

    //全局参数绑定
    @ModelAttribute
    public void presetParam(Model model){
        model.addAttribute("globalAttr","this is a global attribute");
    }

    @ModelAttribute()
    public Map<String, String> presetParam(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        return map;
    }

}
