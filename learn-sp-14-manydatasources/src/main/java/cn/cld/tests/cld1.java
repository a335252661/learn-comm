package cn.cld.tests;

import cn.cld.helps.DBConn;
import cn.cld.helps.SQLHelp;

import java.sql.Connection;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2020/9/16
 */
public class cld1 {
    public static void main(String[] args) {
        Connection localConn = DBConn.getLocalConn();

        String s = SQLHelp.querySQLReturnField(localConn, "select count(1) as FIELD from cld_err where rownum =1");

        System.out.println(s);

    }
}
