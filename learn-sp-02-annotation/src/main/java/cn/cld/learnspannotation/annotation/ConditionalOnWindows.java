package cn.cld.learnspannotation.annotation;

import cn.cld.learnspannotation.annlearn.conditionBean.WindowsCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/28
 */
@ConditionalOnClass(WindowsCondition.class)
public @interface ConditionalOnWindows {
}
