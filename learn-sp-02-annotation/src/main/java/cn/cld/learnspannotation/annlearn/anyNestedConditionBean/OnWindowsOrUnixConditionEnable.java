package cn.cld.learnspannotation.annlearn.anyNestedConditionBean;

import cn.cld.learnspannotation.annlearn.conditionBean.LinuxCondition;
import cn.cld.learnspannotation.annlearn.conditionBean.WindowsCondition;
import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.ConfigurationCondition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/28
 */

/**
 * 如果我们想要使用"OR"将多个条件合并为一个条件，我们可以继承AnyNestedCondition：
 * 表示 @Conditional(LinuxCondition.class) 和  @Conditional(WindowsCondition.class)  只要成立一个
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Conditional({OnWindowsOrUnixConditionEnable.OnWindowsOrUnixCondition.class}) // 这个类存在，Conditional是用有条件的加载bean
public @interface OnWindowsOrUnixConditionEnable {

    public static class OnWindowsOrUnixCondition extends AllNestedConditions {
        public OnWindowsOrUnixCondition() {
             super(ConfigurationPhase.REGISTER_BEAN);
        }

        @Conditional(LinuxCondition.class)
        static class OnWindows {}

        @Conditional(WindowsCondition.class)
        static class OnUnix {}

    }

}
