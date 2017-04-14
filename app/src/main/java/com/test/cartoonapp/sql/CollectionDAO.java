package com.test.cartoonapp.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.test.cartoonapp.bean.CollectionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC-2 on 2017/4/14.
 */

public class CollectionDAO {
    private SQLiteDatabase helper;
    private DB db;
    public CollectionDAO(Context context){
        db = new DB(context);
    }
    public boolean save(CollectionBean bean){
        helper = db.getWritableDatabase();
        ContentValues cv  = new ContentValues();
        cv.put("name",bean.getName());
        cv.put("url",bean.getUrl());
        long result = helper.insert(DB.TABLENAME,null,cv);
        return result == -1 ? false : true;
    }
    public List<CollectionBean> list(){
        List<CollectionBean> list = new ArrayList<>();
        helper = db.getReadableDatabase();
        Cursor c = helper.query(false,DB.TABLENAME,null,null,null,null,null,null,null);
        CollectionBean bean  = null;
        while(c.moveToNext()){
            bean = new CollectionBean();
            bean.setId(c.getInt(c.getColumnIndex("id")));
            bean.setName(c.getString(c.getColumnIndex("name")));
            bean.setUrl(c.getString(c.getColumnIndex("url")));
            list.add(bean);
        }
        return list;
    }
    public boolean delete(int id){
        helper = db.getWritableDatabase();
        int resultCode = helper.delete(DB.TABLENAME,"id=?",new String[]{String.valueOf(id)});
        return resultCode == 0 ? false : true;
    }

    public CollectionBean list(int id){
        helper = db.getReadableDatabase();
        Cursor c  =helper.query(false,DB.TABLENAME,null,"id=?",new String[]{String.valueOf(id)},null,null,null,null);
        CollectionBean bean = null;
        while(c.moveToNext()){
            bean = new CollectionBean();
            bean.setId(c.getInt(c.getColumnIndex("id")));
            bean.setName(c.getString(c.getColumnIndex("name")));
            bean.setUrl(c.getString(c.getColumnIndex("url")));
        }
        return bean;
    }
    public boolean judge(String url){
        Log.e("test-judge",url);
        helper = db.getReadableDatabase();
        Cursor c  = helper.query(false,DB.TABLENAME,null,"url=?",new String[]{url},null,null,null,null);
        while(c.moveToNext()){
            return false;
        }
        return true;

    }
}
