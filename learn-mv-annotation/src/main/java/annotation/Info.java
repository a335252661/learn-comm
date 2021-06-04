package annotation;

import java.lang.annotation.*;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/5/6
 */
@Documented
@Target(ElementType.TYPE) //表示可以用在类上
@Retention(RetentionPolicy.RUNTIME) //在运行时有效（即运行时保留）
public @interface Info {
    String name() default "cld";
    int age() default 18;
    String description() default "默认的描述";
    String[] hobby() default {"basketball", "football"};
}
