package cn.cld.learnsp18start.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/7/28
 */
// 自动导入application.properties配置文件中的属性
@ConfigurationProperties(prefix = "cld.demo")
public class CldDemoProperties {

    private String name = "默认";
    private String address = "默认地址";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
