package cn.cld.learnspannotation.environment;

import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.env.SystemEnvironmentPropertySource;

import java.util.HashMap;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/7/29
 */
public class cldEnvironment extends StandardEnvironment {

    // systemProperties
    public static final String CLD_TEST_SOURCE= "cld_test_source" ;

    @Override
    protected void customizePropertySources(MutablePropertySources propertySources) {

        HashMap<String , Object> map = new HashMap<String , Object>();
        map.put("name1" , "demo1");
        map.put("name2" , "demo1");
//        propertySources.addLast(
//                new PropertiesPropertySource(CLD_TEST_SOURCE, map));
//        propertySources.addLast(
//                new SystemEnvironmentPropertySource(SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME, getSystemEnvironment()));

        super.customizePropertySources(propertySources);

    }

}
