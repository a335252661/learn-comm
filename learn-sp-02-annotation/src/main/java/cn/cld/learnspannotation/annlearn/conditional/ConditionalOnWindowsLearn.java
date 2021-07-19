package cn.cld.learnspannotation.annlearn.conditional;

import cn.cld.learnspannotation.annotation.ConditionalOnWindows;
import org.springframework.context.annotation.Configuration;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/28
 */
@Configuration
@ConditionalOnWindows
public class ConditionalOnWindowsLearn {
    public ConditionalOnWindowsLearn(){
        System.out.println("ConditionalOnWindowsLearn---------------------加载");
    }
}
