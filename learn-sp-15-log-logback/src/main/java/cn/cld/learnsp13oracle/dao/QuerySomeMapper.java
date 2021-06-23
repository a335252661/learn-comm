package cn.cld.learnsp13oracle.dao;

import cn.hutool.json.JSONArray;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/1
 */
@Mapper
public interface QuerySomeMapper {

    public List<HashMap> querydb();

}
