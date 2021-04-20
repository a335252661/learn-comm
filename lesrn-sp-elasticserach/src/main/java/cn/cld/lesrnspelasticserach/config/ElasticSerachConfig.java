package cn.cld.lesrnspelasticserach.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/1/23
 */
@Configuration
public class ElasticSerachConfig {

    //获取对象
    //放入到spring中待用

    //spring <bean id = "restHighLevelClient" class="RestHighLevelClient" >
    //id就是方法名字
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("127.0.0.1",9200,"http")
                )
        );
        return restHighLevelClient;
    }
}
