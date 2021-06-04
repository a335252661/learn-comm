package cn.cld.lesrnspelasticserach.help;

import cn.cld.lesrnspelasticserach.bean.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/1/23
 */
@Component
public class HtmlHelp {
    public static void main(String[] args) {
        new HtmlHelp().parseJD("java").forEach(System.out::println);
    }

    public ArrayList<Content> parseJD(String keyword) {
        ArrayList<Content> contentArrayList = new ArrayList<>();
        try {
            String url = "https://search.jd.com/Search?keyword=" + keyword;
            Document document = Jsoup.parse(new URL(url), 30000);
            Element element = document.getElementById("J_goodsList");

//            System.out.println(element.html());

            //获取所有的li标签
            Elements elementsByTag = element.getElementsByTag("li");
            //循环标签
            for (Element element1 : elementsByTag) {
                //获取价格 p-price


                //       <div class="p-price">
                //          <strong class="J_12163091" data-done="1"> <em>￥</em><i>52.30</i> </strong>
                //      </div>
                String price = element1.getElementsByClass("p-price").eq(0).text();

                //      <div class="p-name">
                //       <a target="_blank" title="编程三剑客：java+c语言+python 入门经典（京东套装共3册）" href="//item.jd.com/12920564.html" onclick="searchlog(1, '12555860','0','1','','flagsClk=419904')">
                //       <em>编程三剑客：<font class="skcolor_ljg">java</font>+c语言+python 入门经典（京东套装共3册）</em>
                //       <i class="promo-words" id="J_AD_12920564"></i>
                //       </a>
                //      </div>
                String name = element1.getElementsByClass("p-name").eq(0).text();
                //一般图片都是懒加载的 ，
//                String img  = element1.getElementsByTag("img").eq(0).attr("src");
                //      <div class="p-img">
                //       <a target="_blank" title="Java经典学习套装（京东套装共2册）" href="//item.jd.com/12273578.html" onclick="searchlog(1, '12185501','3','2','','flagsClk=2097626')">
                //       <img width="" height="" data-img="1" src="//img13.360buyimg.com/n1/s200x200_jfs/t1/140675/7/14882/176740/5fb334aeE3c104b7b/6958befd440a1e6c.jpg" data-lazy-img="done"> </a>
                //       <div class="picon pi0">
                //        <b></b>
                //       </div>
                String img = element1.getElementsByTag("img").eq(0).attr("data-lazy-img");

                //店铺 p-shopnum
                //      <div class="p-shopnum" data-dongdong="" data-selfware="1" data-score="5" data-reputation="98">
                //       <a class="curr-shop hd-shopname" target="_blank" onclick="searchlog(1,'1000004111',0,58)" href="//mall.jd.com/index-1000004111.html?from=pc" title="清华大学出版社">清华大学出版社</a>
                //      </div>
                String shopname = element1.getElementsByClass("p-shopnum").eq(0).text();

                System.out.println("-----------------------------------------------------");
                System.out.println(price);
                System.out.println(name);
                System.out.println(img);
                System.out.println(shopname);


                Content content = new Content(name, img, price, shopname);
                contentArrayList.add(content);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return contentArrayList;
    }

}
