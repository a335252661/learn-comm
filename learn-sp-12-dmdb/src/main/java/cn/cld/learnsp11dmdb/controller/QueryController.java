package cn.cld.learnsp11dmdb.controller;

import cn.cld.learnsp11dmdb.dao.TsfUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/5/27
 */
@RestController
public class QueryController {

    @Autowired
    private TsfUserDao userDao;

    @RequestMapping("query")
    public String fun() {
        String token= "";
        try {
             token = userDao.query("test-user-1");
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

        return token;
    }

}
