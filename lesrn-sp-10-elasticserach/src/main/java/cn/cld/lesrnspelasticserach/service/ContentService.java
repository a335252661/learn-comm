package cn.cld.lesrnspelasticserach.service;

import cn.cld.lesrnspelasticserach.bean.Content;
import cn.cld.lesrnspelasticserach.bean.User;
import cn.cld.lesrnspelasticserach.help.HtmlHelp;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/1/23
 */
//业务编写
@Service

public class ContentService {

    @Autowired
    @Qualifier("restHighLevelClient")        // 对应方法名字
            RestHighLevelClient client;

    //把数据放入es
    public Boolean parseContent(String keywords) throws Exception {
        ArrayList<Content> contents = new HtmlHelp().parseJD(keywords);

        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");

        for (int i = 0; i < contents.size(); i++) {
            IndexRequest indexrequest = new IndexRequest("jd_index");
            indexrequest.id(i + 1 + "");
            indexrequest.source(JSON.toJSONString(contents.get(i)), XContentType.JSON);

            bulkRequest.add(indexrequest);
        }

        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        return !bulkResponse.hasFailures();
    }

    public List<Map<String, Object>> searchPage(String keyword, int page, int size) throws Exception{
        SearchRequest searchRequest = new SearchRequest("jd_index");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //分页
        searchSourceBuilder.from(page);
        searchSourceBuilder.size(size);

        //精准匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(3000));

        //执行搜索
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            list.add(hit.getSourceAsMap());
        }

        return list;
    }

    public List<Map<String, Object>> searchPageHighLight(String keyword, int page, int size) throws Exception{
        SearchRequest searchRequest = new SearchRequest("jd_index");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //分页
        searchSourceBuilder.from(page);
        searchSourceBuilder.size(size);

        //精准匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(3000));

        //增加高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        highlightBuilder.requireFieldMatch(false);//关闭多个字段高亮，只会第一个匹配到的高亮
        searchSourceBuilder.highlighter(highlightBuilder);

        //执行搜索
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {

            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title = highlightFields.get("title");

            //原来返回的结果
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();


            String htmlTitle = "";  //新的带有html标签的 title字段
            if(null!=title){
                Text[] fragments = title.fragments();
                for (Text text : fragments) {
                    htmlTitle += text;
                }
            }
            sourceAsMap.put("title" , htmlTitle);

//            sourceAsMap.put("title",
//                    Stream.of(title.fragments())
//                            .map(Text :: toString)
//                            .collect(Collectors.joining(""))
//            );


            list.add(sourceAsMap);
        }

        return list;
    }

    public List<Map<String, Object>> searchES(String index ,String key ,  String keyword, int page, int size) throws Exception{
        SearchRequest searchRequest = new SearchRequest(index);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //分页
        searchSourceBuilder.from(page);
        searchSourceBuilder.size(size);

        //精准匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(key, keyword);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(3000));
        //执行搜索
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            list.add(hit.getSourceAsMap());
        }

        return list;
    }

}
