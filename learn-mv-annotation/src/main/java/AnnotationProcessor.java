import annotation.Gender;
import annotation.Info;
import entity.User;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/5/7
 */
public class AnnotationProcessor {

    public static void parseAnnotation(Class<?> clazz) {
        //判断该类上是否有 Info 注解
        if(clazz.isAnnotationPresent(Info.class)){
            //获取info注解
            Info annotation = clazz.getAnnotation(Info.class);
            System.out.println(annotation);

            System.out.println(annotation.name());
            System.out.println(annotation.age());
            String[] bobby = annotation.hobby();
            for(String str : bobby){
                System.out.println(str);
        }
        }

        //判断字段上是否有 对应注解
        Field[] declaredFields = clazz.getDeclaredFields();
        if(declaredFields.length >0 ){
            for(Field field : declaredFields){
                if(field.isAnnotationPresent(Gender.class)){
                    Gender annotation = field.getAnnotation(Gender.class);
                    System.out.println(annotation);
                    System.out.println(annotation.gender());
                }
            }
        }

    }

    public static void main(String[] args) {
        AnnotationProcessor.parseAnnotation(User.class);
    }

}
