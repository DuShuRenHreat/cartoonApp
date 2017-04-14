package com.test.cartoonapp;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC-2 on 2017/4/14.
 */

public class MyAppliaction extends Application{
    private List<String> UrlList;
    @Override
    public void onCreate() {
        super.onCreate();
        UrlList = new ArrayList<>();
    }
    public int getListCount(){
        return UrlList.size();
    }
    public void putValue(String url){
        UrlList.add(url);
    }
    public String getValue(int index){
        return UrlList.get(index);
    }


}
