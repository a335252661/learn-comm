package cn.cld.learnspannotation.annlearn.conditional;

import cn.cld.learnspannotation.annlearn.anyNestedConditionBean.OnWindowsOrUnixConditionEnable;
import org.springframework.context.annotation.Configuration;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/28
 */
@OnWindowsOrUnixConditionEnable
@Configuration
public class OnWindowsOrUnixConditionEnableLearn {
    public OnWindowsOrUnixConditionEnableLearn(){
        System.out.println("OnWindowsOrUnixConditionEnableLearn---------------------加载");
    }
}
