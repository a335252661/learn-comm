package cn.cld.lesrnspelasticserach.controller;

import cn.cld.lesrnspelasticserach.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/1/23
 */
@RestController
public class ContentController {

    @Autowired
    private ContentService contentService;

    //返回true ， 数据插入成功
    @GetMapping("/parse/{keywords}")
    public Boolean parse(@PathVariable("keywords") String keywords) {
        Boolean aBoolean = true;
        try {
            aBoolean = contentService.parseContent(keywords);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aBoolean;
    }

    //从es 里面获取数据
    // localhost:8080/search/java/1/29
    @GetMapping("/search/{keyword}/{page}/{size}")
    public List<Map<String, Object>> searchPage(@PathVariable("keyword") String keyword,
                                                @PathVariable("page") int page,
                                                @PathVariable("size") int size) {
        List<Map<String, Object>> list = null;
        try {
            list = contentService.searchPage(keyword, page, size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //高亮显示
    @GetMapping("/searchligth/{keyword}/{page}/{size}")
    public List<Map<String, Object>> searchPageHighLight(@PathVariable("keyword") String keyword,
                                                @PathVariable("page") int page,
                                                @PathVariable("size") int size) {
        List<Map<String, Object>> list = null;
        try {
            list = contentService.searchPageHighLight(keyword, page, size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //从es 里面获取数据
    // localhost:8080/search/java/1/29
    // localhost:8080/search/filebeat-7.6.2-2021.06.16-000001/message/provider/1/29
    @GetMapping("/search/{index}/{key}/{keyword}/{page}/{size}")
    public List<Map<String, Object>> searchEs(@PathVariable("index") String index,
                                              @PathVariable("key") String key,
                                              @PathVariable("keyword") String keyword,
                                                @PathVariable("page") int page,
                                                @PathVariable("size") int size) {
        List<Map<String, Object>> list = null;
        try { //String index ,String key ,  String keyword
            list = contentService.searchES(index , key , keyword, page, size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
