package com.test.cartoonapp;

import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.test.cartoonapp.utils.InitID;

public class MainActivity extends BaseActivity {
    private boolean isExit = false;
    private Handler exithandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = true;
        }
    };
    @InitID(R.id.main_wv)
    private WebView wv;
    @Override
    public void doSomething() {
        wv.loadUrl("http://m.dilidili.wang/");
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setPluginState(WebSettings.PluginState.ON);
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wv.getSettings().setAllowFileAccess(true);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wv.setWebChromeClient(new WebChromeClient(){

        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK){
            wv.goBack();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit(){
        if(!isExit){
            isExit = true;
            Toast.makeText(this, "再点击一次退出程序", Toast.LENGTH_SHORT).show();
            exithandler.sendEmptyMessageAtTime(200,2000);
        }else{
           System.exit(0);

        }
    }
    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public String setPreName() {
        return null;
    }

    @Override
    public String setNextName() {
        return null;
    }

    @Override
    public void doPreSomething() {

    }

    @Override
    public void doNextSomething() {

    }

    @Override
    public String setTitle() {
        return "标题";
    }

}
