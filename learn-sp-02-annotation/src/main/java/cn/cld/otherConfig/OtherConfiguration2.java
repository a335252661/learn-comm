package cn.cld.otherConfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/18
 */
@Configuration
@Slf4j
// 不在启动程序的同级目录下，不会被加载到
public class OtherConfiguration2 {
    public OtherConfiguration2() {
        log.info("oher目录下的配置初始化----OtherConfiguration--------------------------2222222");
    }
}
