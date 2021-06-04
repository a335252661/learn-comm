package cn.cld.lesrnspelasticserach;

import cn.cld.lesrnspelasticserach.bean.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 *
 * 关于文档的操作
 *
 */
@SpringBootTest
class DocumentTests {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Autowired
    @Qualifier("restHighLevelClient")        // 对应方法名字
    RestHighLevelClient client;

    //向索引的默认文档类型_doc 里面添加数据
    //PUT sp_index/_doc/1
    //    {
    //        "name":"橙子",
    //         "age":22
    //    }
    @Test
    void fun() throws Exception{
        User user = new User("橙子", 22);
        //获取索引
        IndexRequest sp_index = new IndexRequest("sp_index");

        //添加数据
        sp_index.id("1");
        sp_index.timeout(TimeValue.timeValueMinutes(1));
        sp_index.source(JSON.toJSONString(user) , XContentType.JSON);

        //客户端发送请求
        IndexResponse index = client.index(sp_index, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(index));

        //{"fragment":false,"id":"1","index":"sp_index","primaryTerm":1,"result":"CREATED","seqNo":0,"shardId":{"fragment":true,"id":-1,"index":{"fragment":false,"name":"sp_index","uUID":"_na_"},"indexName":"sp_index"},"shardInfo":{"failed":0,"failures":[],"fragment":false,"successful":1,"total":2},"type":"_doc","version":1}


    }

    //判断文档是否存在
    @Test
    public void fun2() throws Exception {
        GetRequest getRequest = new GetRequest("sp_index", "1");
        //不获取返回的_source的上下文
        getRequest.fetchSourceContext(new FetchSourceContext(false));
//        getRequest.storedFields("_none_");//排序
        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    //获取文档
    @Test
    public void fun3() throws Exception {
        GetRequest getRequest = new GetRequest("sp_index", "1");
        GetResponse getresponse = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(getresponse));
        System.out.println(getresponse.getSourceAsString());
        System.out.println(getresponse);

    }

    //跟新文档
    @Test
    public void fun4() throws Exception {
        UpdateRequest updateRequest = new UpdateRequest("sp_index", "1");
        updateRequest.timeout("1s");
        User user = new User("更新的橙子", 33);

        updateRequest.doc(JSON.toJSONString(user) , XContentType.JSON);

        UpdateResponse update = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(update);
    }

    //删除文档
    @Test
    public void fun5() throws Exception {
        DeleteRequest deleteRequest = new DeleteRequest("sp_index", "1");
        deleteRequest.timeout("1s");
        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(deleteResponse);//delete {[sp_index][_doc][1]}
    }

    //批量插入数据
    @Test
    public void fun6() throws Exception {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2s");
        List<User> users = Arrays.asList(
                new User("hello world1", 1),
                new User("hello world2", 1),
                new User("hello world3", 1),
                new User("hello world4", 1)
        );

        for (int i = 0; i < users.size(); i++) {
            IndexRequest indexrequest = new IndexRequest("sp_index");
            indexrequest.id(i+1+"");
            indexrequest.source(JSON.toJSONString(users.get(i)) , XContentType.JSON);

            bulkRequest.add(indexrequest);
        }


        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkResponse);

    }

    //加条件查询文档
    @Test
    public void fun7() throws Exception {
        SearchRequest searchRequest = new SearchRequest("sp_index");
        //构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

//        TermQueryBuilder termQueryBuilder = QueryBuilders.matchQuery("name", "批量");

        //查询所有
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();

        searchSourceBuilder.query(matchAllQueryBuilder);
        searchSourceBuilder.timeout( new TimeValue(60, TimeUnit.SECONDS));
        //分页
        searchSourceBuilder.from(1);
        searchSourceBuilder.size(50);

        searchRequest.source(searchSourceBuilder);

        //执行搜索
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        System.out.println("-----------------------------------------------------");
        searchResponse.getHits().forEach(System.out::println);

    }

}
