package cn.cld.learnspshiro;

import cn.cld.learnspshiro.dao.UserDAO;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
class LearnSpShiroApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    UserDAO mapper;

    @Test
    void contextLoads() throws Exception{
        System.out.println(dataSource.getConnection());


        List<JSONObject> qwer = mapper.queryRoleByUserName("qwer");
        System.out.println(qwer.get(0).toJSONString());

        List<JSONObject> jsonObjects = mapper.queryPermsByRoleName("user");
        System.out.println(jsonObjects.get(0).toJSONString());
    }

}
