package cn.cld.learnsp01test.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/5/7
 */
@RestController
public class HelloController {

    @Autowired
    RestTemplate restTemplate;


    @RequestMapping("/hello")
    @ResponseBody
    public JSONObject fun(HttpServletRequest request) {
        JSONObject js = new JSONObject();
        JSONObject js2 = new JSONObject();
        Enumeration er = request.getHeaderNames();//获取请求头的所有name值
        while(er.hasMoreElements()){
            String name	=(String) er.nextElement();
            String value = request.getHeader(name);
            js2.put(name,value);
        }
        js.put("请求方的http headers信息" , js2);
        //请求头 signature
        String signature = request.getHeader("x-rio-signature");
        String apisign = this.apisign(request);
        js.put("程序本地计算的签名" , apisign);
        if(!signature.equals(apisign)){
            js.clear();
            js.put("ERROR","请求头 signature与程序本地计算的签名不相等");
        }
        return js;
    }
    public String apisign(HttpServletRequest request) {
        String Token = "cld7758258";
        String signature = request.getHeader("x-rio-signature");
        String timestamp = request.getHeader("x-rio-timestamp");
        String nonce = request.getHeader("x-rio-nonce");
        String uid = request.getHeader("x-rio-uid");
        String uinfo = request.getHeader("x-rio-uinfo");
        String ext = request.getHeader("x-rio-ext");
        String signature_sign = getSHA256(timestamp+Token+nonce+timestamp).toUpperCase();
        return signature_sign;
    }

    public  String getSHA256(String str){
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodestr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodestr;
    }
    /**
     * 将byte转为16进制
     * @param bytes
     * @return
     */
    private  String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }


    public static void main(String[] args) {
        //获取网关请求头
        //x-rio-signature ---- AD92AE780900653E9E7E5F476177D08B9C88374E95121B76DB2E7DC4ECCEB0ED
        //x-rio-timestamp ---- 1625555723
        //x-rio-nonce ---- 580a000a:017a7aabe309:02005a
        //计算签名------------------------------------------
        //1625555723cld7758258580a000a:017a7aabe309:02005a1625555723
        //signature_sign=2df883f881c139ff7b0c4a209f20ede1d56b7b7e419d34df27f96ed0b9bdecba


    }

}
