package cn.cld.learnsp13oracle.service.impl;

import cn.cld.learnsp13oracle.dao.QuerySomeMapper;
import cn.cld.learnsp13oracle.service.QuerySome;
import cn.hutool.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/1
 */
@Service
public class QuerySomeImpl implements QuerySome {

    @Autowired
    private QuerySomeMapper querySomeMapper;

    @Override
    public List<HashMap> queryData() {
        List<HashMap> js = querySomeMapper.querydb();
        return js;
    }
}
