package cn.cld.learnspannotation.service.impl;

import cn.cld.learnspannotation.service.Fruit;
import org.springframework.stereotype.Component;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/18
 */
@Component
public class apple implements Fruit {
    @Override
    public void color() {
        System.out.println("青色");
    }
}
