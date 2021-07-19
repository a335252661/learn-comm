package cn.cld.learnspannotation.annlearn.anyNestedConditionBean;

import cn.cld.learnspannotation.annlearn.conditionBean.LinuxCondition;
import cn.cld.learnspannotation.annlearn.conditionBean.WindowsCondition;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/28
 */
//如果我们想要使用"OR"将多个条件合并为一个条件，我们可以继承AnyNestedCondition：
public class OnWindowsOrUnixCondition extends AnyNestedCondition {
    public OnWindowsOrUnixCondition(ConfigurationPhase configurationPhase) {
//        super(configurationPhase);
        super(ConfigurationPhase.REGISTER_BEAN);
    }

    @Conditional(LinuxCondition.class)
    class OnWindows {}

    @Conditional(WindowsCondition.class)
    static class OnUnix {}

}
