package com.test.cartoonapp.net;

import com.test.cartoonapp.bean.ItemBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC-2 on 2017/4/11.
 */

public class HTTPAnalysis {
    public static List<ItemBean> getList(String html){
            Document document = Jsoup.parse(HTTPRequest.request("http://www.1kkk.com/"));//url
            Elements elements = document.select(".pic > li");
            ArrayList<ItemBean> beans = new ArrayList<>();
            for(Element e : elements) {
                ItemBean bean = new ItemBean();
                String str1 = e.select("a").attr("href");
                String jg = str1.substring(8, 12);
                bean.setId(Integer.valueOf(jg));
                String picUrl = e.select("img").attr("src");
                bean.setPicUri(picUrl);
                String title = e.select("img").attr("alt");
                bean.setTitle(title);
                beans.add(bean);
            }
        return beans;
    }
    public static List<ItemBean> getList1(String html){
        String str = HTTPRequest.request("http://www.1kkk.com/");
        Document document = Jsoup.parse(str);
        Elements elements = document.select(".newBox > ul > li ");
        ArrayList<ItemBean> beans = new ArrayList<>();
        for(Element e : elements){
            ItemBean bean = new ItemBean();
            String picUrl = e.select("img").attr("src");
            bean.setPicUri(picUrl);
            String title = e.select("img").attr("alt");
            bean.setTitle(title);
            beans.add(bean);
        }
        return beans;
    }

}
