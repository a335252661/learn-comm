package annotation;

import java.lang.annotation.*;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/5/6
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Gender {
    public enum GenderEnum{BOY, GIRL}
    GenderEnum gender() default GenderEnum.BOY;
}
