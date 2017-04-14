package com.test.cartoonapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.test.cartoonapp.R;
import com.test.cartoonapp.bean.CollectionBean;

import java.util.List;

/**
 * Created by PC-2 on 2017/4/14.
 */

public class SlidAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<CollectionBean> list;
    public SlidAdapter(Context context,List<CollectionBean> list){
        inflater = LayoutInflater.from(context);
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(R.layout.slid_item,null);
        }
        TextView tv = (TextView) view.findViewById(R.id.sliditem_tv);
        CollectionBean bean = list.get(i);
        tv.setText(bean.getName());
        return view;
    }
}
