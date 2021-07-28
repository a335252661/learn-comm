package cn.cld.learnsp18start.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/7/28
 */
@Configuration
public class CldTestConfiguration {
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
