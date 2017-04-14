package com.test.cartoonapp.bean;

/**
 * Created by PC-2 on 2017/4/14.
 */

public class CollectionBean {
    private int id = 0 ;
    private String name;
    private String url;
    public CollectionBean(){}
    public CollectionBean(String name,String url){
        this.name = name;
        this.url = url;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
