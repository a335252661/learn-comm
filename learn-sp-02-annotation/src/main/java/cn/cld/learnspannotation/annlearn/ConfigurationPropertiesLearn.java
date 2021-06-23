package cn.cld.learnspannotation.annlearn;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/10
 */
@Component
@Data
// 当我们为属性配置错误的值时，而又不希望 Spring Boot 应用启动失败，我们可以设置 ignoreInvalidFields = true
// 获取配置文件配置
@ConfigurationProperties(prefix = "myapp.mail" , ignoreInvalidFields = true)
public class ConfigurationPropertiesLearn {
    //myapp.mail.host=192.168.1.1
    //myapp.mail.name=cld

    public String host;
    public String name;

}
