package com.test.cartoonapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.test.cartoonapp.utils.InitView;

/**
 * Created by PC-2 on 2017/4/11.
 */

public abstract class BaseActivity extends Activity{
    private TextView title;
    private Button btnpre;
    private Button btnnext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(setLayoutId());
        InitView.load(this);
        View v = LayoutInflater.from(this).inflate(R.layout.titilebar,null);
        title = (TextView) findViewById(R.id.titbar_tv_title);
        btnpre = (Button) findViewById(R.id.titbar_btn_pre);
        btnnext = (Button) findViewById(R.id.titbar_btn_next);
        title.setText(setTitle());
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doPreSomething();
            }
        });
        btnpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doNextSomething();
            }
        });
        if(null == setPreName()){
            btnpre.setVisibility(View.INVISIBLE);
        }else{
            btnpre.setText(setPreName());
        }
        if(null == setNextName()){
            btnnext.setVisibility(View.INVISIBLE);
        }else{
            btnnext.setText(setNextName());
        }
        this.doSomething();

    }
    public abstract  void doSomething();
    public abstract int setLayoutId();
    public abstract String setPreName();
    public abstract String setNextName();
    public abstract void doPreSomething();
    public abstract void doNextSomething();
    public abstract String setTitle();



}
