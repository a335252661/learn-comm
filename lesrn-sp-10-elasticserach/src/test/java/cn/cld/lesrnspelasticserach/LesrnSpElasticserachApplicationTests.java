package cn.cld.lesrnspelasticserach;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;


/**
 *
 * 关于索引的操作
 *
 */
@SpringBootTest
class LesrnSpElasticserachApplicationTests {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Autowired
    @Qualifier("restHighLevelClient")        // 对应方法名字
    RestHighLevelClient client;

    //创建索引  PUT sp_index
    @Test
    void fun() throws Exception{
        //创建索引请求
        CreateIndexRequest sp_index = new CreateIndexRequest("sp_index");
        //客户端执行请求
        CreateIndexResponse createIndexResponse = client.indices().create(sp_index, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

    //判断索引是否存在
    @Test
    public void fun2() throws Exception{
        GetIndexRequest sp_index = new GetIndexRequest("sp_index");
        boolean exists = client.indices().exists(sp_index, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    //删除索引
    @Test
    public void fun3() throws Exception {
        DeleteIndexRequest sp_index = new DeleteIndexRequest("sp_index");
        AcknowledgedResponse delete = client.indices().delete(sp_index, RequestOptions.DEFAULT);
        String s = JSONObject.toJSONString(delete);
        System.out.println(s);
    }

}
