package com.test.cartoonapp.bean;

/**
 * Created by PC-2 on 2017/4/11.
 */

public class ItemBean {
    private int id;
    //标题
    private String title;
    //图片
    private String picUri;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUri() {
        return picUri;
    }

    public void setPicUri(String picUri) {
        this.picUri = picUri;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    //看到哪里
    private int index = 0;
    //数量
    private int num;

}
