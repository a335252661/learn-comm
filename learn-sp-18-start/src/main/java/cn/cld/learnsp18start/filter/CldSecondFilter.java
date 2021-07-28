package cn.cld.learnsp18start.filter;

import org.springframework.core.Ordered;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/7/28
 */
public class CldSecondFilter implements Ordered {

    // 越小越先执行
    @Override
    public int getOrder() {
        return 0;
    }
}
