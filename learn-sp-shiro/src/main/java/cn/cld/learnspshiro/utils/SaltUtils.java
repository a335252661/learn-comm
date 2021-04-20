package cn.cld.learnspshiro.utils;

import com.alibaba.druid.support.json.JSONUtils;

import java.util.Random;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/2/7
 */
public class SaltUtils {
    public static String getSalt(int n ) {

        char[] chars = "ABCDEFGabcdefg123456!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Random().nextInt(40000));
        System.out.println(getSalt(10));

    }

}
