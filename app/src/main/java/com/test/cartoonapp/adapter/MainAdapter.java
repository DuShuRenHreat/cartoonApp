package com.test.cartoonapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.cartoonapp.R;
import com.test.cartoonapp.bean.ItemBean;

import java.util.List;

/**
 * Created by PC-2 on 2017/4/11.
 */

public class MainAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<ItemBean> beans;
    private Context context;
    public MainAdapter(Context context, List<ItemBean> beans){
        inflater = LayoutInflater.from(context);
        this.beans = beans;
        this.context = context;
    }
    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(R.layout.main_item,null);
        }
        ItemBean bean = beans.get(i);
        ImageView iv = (ImageView) view.findViewById(R.id.mainitem_iv);
        TextView tv = (TextView) view.findViewById(R.id.mainitem_tv);
        Picasso.with(context)
                .load(bean.getPicUri())
                .priority(Picasso.Priority.HIGH)
                .into(iv);
        tv.setText(bean.getTitle() + "");
        return view;
    }
}
