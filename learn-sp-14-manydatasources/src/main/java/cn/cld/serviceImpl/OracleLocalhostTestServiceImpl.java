package cn.cld.serviceImpl;

import cn.cld.mapper.mysql_localhost.MysqlLocalhostMapper;
import cn.cld.mapper.oracle_localhost.OracleLocalhostMapper;
import cn.cld.service.OracleLocalhostTestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2020/9/16
 */
@Service
public class OracleLocalhostTestServiceImpl implements OracleLocalhostTestService {
    @Resource
    private OracleLocalhostMapper oracleLocalhostMapper;
    @Resource
    private MysqlLocalhostMapper mysqlLocalhostMapper;
    @Override
    public String fun1() {

        String mm =  mysqlLocalhostMapper.test2();

        return oracleLocalhostMapper.test()+"=========="+mm;
    }
}
