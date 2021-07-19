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
    public String fun() {
        return "HellowWord!";
    }

    @RequestMapping("/getRequest")
    @ResponseBody
    public JSONObject fun2(HttpServletRequest request) {
        String Token = "cld7758258";
        StringBuilder sb = new StringBuilder();
        JSONObject js = new JSONObject();

        JSONObject js2 = new JSONObject();
        Enumeration er = request.getHeaderNames();//获取请求头的所有name值
        while(er.hasMoreElements()){
            String name	=(String) er.nextElement();
            String value = request.getHeader(name);
            js2.put(name,value);
        }
        js.put("请求方的http headers信息" , js2);
        sb.append("<br/>");
        sb.append("<br/>");
        sb.append("<br/>");
        sb.append("<br/>");
        sb.append("获取网关请求头");
        sb.append("<br/>");
        String signature = request.getHeader("x-rio-signature");
        sb.append("x-rio-signature  ----  ").append(signature).append("<br/>");
        String timestamp = request.getHeader("x-rio-timestamp");
        sb.append("x-rio-timestamp  ----  ").append(timestamp).append("<br/>");
        String nonce = request.getHeader("x-rio-nonce");
        sb.append("x-rio-nonce  ----  ").append(nonce).append("<br/>");
        String uid = request.getHeader("x-rio-uid");
        sb.append("x-rio-uid  ----  ").append(nonce).append("<br/>");
        String uinfo = request.getHeader("x-rio-uinfo");
        sb.append("x-rio-uinfo  ----  ").append(nonce).append("<br/>");
        String ext = request.getHeader("x-rio-ext");
        sb.append("x-rio-ext  ----  ").append(nonce).append("<br/>");
        sb.append("计算签名------------------------------------------").append("<br/>");
        sb.append(timestamp+Token+nonce+timestamp).append("<br/>");
//        String signature_sign = getSHA256(timestamp+Token+nonce+timestamp);
//        sb.append("signature_sign=" + signature_sign).append("<br/>");

        String signature_sign_2 = getSHA256(timestamp+Token+nonce+","+uid+","+uinfo+","+ext+timestamp).toUpperCase();
        sb.append("signature_sign_2=" + signature_sign_2).append("<br/>");

        //程序本地计算的签名
        js.put("程序本地计算的签名",signature_sign_2);
        System.out.println("app1_site1_cld  开始调用   app1_srv1_cld");
        JSONObject togateway = this.togateway();
        js.put("app1_srv1_cld 的返回信息 ",togateway);
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

    public JSONObject togateway() {
        String timestamp=new Date().getTime()/1000+"";
        String nonce=getSHA256(timestamp).substring(0,8);
        String paasid = "app1_cld"; //我需要调用接口api ， 这个是注册在里约管理平台的api网关 应用标识/PaasId (必填)
        String Token = "cld7758258";
        String signature_sign = getSHA256(timestamp+Token+nonce+timestamp);
        String body = HttpRequest.get("http://wysun.cn/ebus/app1_cld/togateway/")
                .header("x-rio-paasid", paasid)
                .header("x-rio-timestamp", timestamp)
                .header("x-rio-nonce", nonce)
                .header("x-rio-signature", signature_sign).execute().body();
        JSONObject jj = new JSONObject(body);
        return jj;
    }

    @RequestMapping("/togateway")
    @ResponseBody
    public JSONObject fun3(HttpServletRequest request) {

        JSONObject js = new JSONObject();
        JSONObject js2 = new JSONObject();
        Enumeration er = request.getHeaderNames();//获取请求头的所有name值
        while(er.hasMoreElements()){
            String name	=(String) er.nextElement();
            String value = request.getHeader(name);
            js2.put(name,value);
        }
        js.put("请求方的http headers信息" , js2);
        String apisign = this.apisign(request);

        System.out.println("app1_site1_cld  调用到   app1_srv1_cld");
        System.out.println("app1_srv1_cld  开始调用   app2_srv1_cld");

        String timestamp=new Date().getTime()/1000+"";
        String nonce=getSHA256(timestamp).substring(0,8);
        String paasid = "app2_cld"; //我需要调用接口api ， 这个是注册在里约管理平台的api网关 应用标识/PaasId (必填)
        String Token = "cld7758258";
        String signature_sign = getSHA256(timestamp+Token+nonce+timestamp);

        js.put("程序本地计算的签名" , signature_sign);

        if(!apisign.equals(signature_sign)){
            System.out.println("程序本地计算的签名 与 请求头处签名不符合");
            return null;
        }
        String body = HttpRequest.get("http://wysun.cn/ebus/app2_cld/hello/")
                .header("x-rio-paasid", paasid)
                .header("x-rio-timestamp", timestamp)
                .header("x-rio-nonce", nonce)
                .header("x-rio-signature", signature_sign).execute().body();
        System.out.println(body);
        JSONObject jj = new JSONObject(body);
        js.put("app2_srv1_cld 返回的信息" , jj);
        return js;
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
//         String timestamp = 1625640235
//        String signature_sign_2 = getSHA256(timestamp+Token+nonce+","+uid+","+uinfo+","+ext+timestamp).toUpperCase();
    }

}
