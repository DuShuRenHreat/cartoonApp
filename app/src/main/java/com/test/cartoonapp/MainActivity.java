package com.test.cartoonapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.slidingmenu.lib.SlidingMenu;
import com.test.cartoonapp.adapter.SlidAdapter;
import com.test.cartoonapp.bean.CollectionBean;
import com.test.cartoonapp.sql.CollectionDAO;

import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{
    private WebView webView;
    private FrameLayout video_fullView;// 全屏时视频加载view
    private View xCustomView;
    private ProgressDialog waitdialog = null;
    private WebChromeClient.CustomViewCallback xCustomViewCallback;
    private myWebChromeClient xwebchromeclient;
    private boolean isExit = false;
    CollectionDAO dao;
    private ListView lv;
    private Button btnss,btnsx;
    private SlidingMenu slidingMeun;
    private Handler exithandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            isExit = true;
        }
    };
    public void exit(){
        if(!isExit){
            isExit = true;
            exithandler.sendEmptyMessage(0);
            Toast.makeText(this, "再点击一次退出程序", Toast.LENGTH_SHORT).show();
        }else{
            System.exit(0);
        }
    }
    public void initProgressDialog(){
        waitdialog = new ProgressDialog(this);
        waitdialog.setTitle("提示");
        waitdialog.setMessage("页面加载中...");
        waitdialog.setIndeterminate(true);
        waitdialog.setCancelable(true);
        waitdialog.show();
    }
    public void initView(){
        dao = new CollectionDAO(this);
        btnss = (Button) findViewById(R.id.main_btn_sc);
        btnsx = (Button) findViewById(R.id.main_btn_sx);
        webView = (WebView) findViewById(R.id.main_wv);
        video_fullView = (FrameLayout) findViewById(R.id.video_fullView);

        btnss.setOnClickListener(this);
        btnsx.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉应用标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initView();
        initProgressDialog();
        initSlidingMeun();
        initWebView();




    }

    public void initWebView(){
        WebSettings ws = webView.getSettings();
        ws.setBuiltInZoomControls(true);// 隐藏缩放按钮
        ws.setUseWideViewPort(true);// 可任意比例缩放
        ws.setLoadWithOverviewMode(true);// setUseWideViewPort方法设置webview推荐使用的窗口。setLoadWithOverviewMode方法是设置webview加载的页面的模式。
        ws.setSavePassword(true);
        ws.setSaveFormData(true);// 保存表单数据
        ws.setJavaScriptEnabled(true);
        ws.setGeolocationEnabled(true);// 启用地理定位
        ws.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");// 设置定位的数据库路径
        ws.setDomStorageEnabled(true);
        ws.setSupportMultipleWindows(true);// 新加
        xwebchromeclient = new myWebChromeClient();
        webView.setWebChromeClient(xwebchromeclient);
        webView.setWebViewClient(new myWebViewClient());
        webView.loadUrl("http://m.dilidili.wang/");
    }

    public void initSlidingMeun(){
        slidingMeun = new SlidingMenu(this);
        slidingMeun.setMode(SlidingMenu.LEFT);
        slidingMeun.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        slidingMeun.setBehindOffsetRes(R.dimen.slid_cc);
        slidingMeun.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        slidingMeun.setMenu(R.layout.slidingmeun);
        lv = (ListView) slidingMeun.findViewById(R.id.slid_lv);
        final List<CollectionBean> list = dao.list();
        lv.setAdapter(new SlidAdapter(this,list));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                webView.loadUrl(list.get(i).getUrl());
                slidingMeun.toggle();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.main_btn_sc:
                if(!dao.judge(webView.getUrl())){
                    Toast.makeText(MainActivity.this, "已存在这个链接", Toast.LENGTH_SHORT).show();
                }else{
                    getDialog();
                }
                break;
            case R.id.main_btn_sx:
                webView.loadUrl(webView.getUrl());
                break;
        }
    }


    public class myWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            waitdialog.dismiss();
        }
    }



    public void getDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText et = new EditText(this);
        builder
                .setView(et)
                .setTitle("请输入备注")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean result = dao.save(new CollectionBean(et.getText().toString(),webView.getUrl()));
                        if(result){
                            Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }



    public class myWebChromeClient extends WebChromeClient {
        private View xprogressvideo;

        // 播放网络视频时全屏会被调用的方法
        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            webView.setVisibility(View.INVISIBLE);
            btnss.setVisibility(View.GONE);
            btnsx.setVisibility(View.GONE);
            // 如果一个视图已经存在，那么立刻终止并新建一个
            if (xCustomView != null) {
                callback.onCustomViewHidden();
                return;
            }
            video_fullView.addView(view);
            xCustomView = view;
            xCustomViewCallback = callback;
            video_fullView.setVisibility(View.VISIBLE);
        }

        // 视频播放退出全屏会被调用的
        @Override
        public void onHideCustomView() {
            if (xCustomView == null)// 不是全屏播放状态
                return;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            xCustomView.setVisibility(View.GONE);
            video_fullView.removeView(xCustomView);
            xCustomView = null;
            video_fullView.setVisibility(View.GONE);
            xCustomViewCallback.onCustomViewHidden();
            webView.setVisibility(View.VISIBLE);
            btnss.setVisibility(View.VISIBLE);
            btnsx.setVisibility(View.VISIBLE);
        }

        // 视频加载时进程loading
        @Override
        public View getVideoLoadingProgressView() {
            if (xprogressvideo == null) {
                LayoutInflater inflater = LayoutInflater
                        .from(MainActivity.this);
                xprogressvideo = inflater.inflate(
                        R.layout.video_loading_progress, null);
            }
            return xprogressvideo;
        }
    }

    /**
     * 判断是否是全屏
     *
     * @return
     */
    public boolean inCustomView() {
        return (xCustomView != null);
    }

    /**
     * 全屏时按返加键执行退出全屏方法
     */
    public void hideCustomView() {
        xwebchromeclient.onHideCustomView();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    @Override
    protected void onResume() {
        super.onResume();
        super.onResume();
        webView.onResume();
        webView.resumeTimers();
        /**
         * 设置为横屏
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
        webView.pauseTimers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        super.onDestroy();
        video_fullView.removeAllViews();
        webView.loadUrl("about:blank");
        webView.stopLoading();
        webView.setWebChromeClient(null);
        webView.setWebViewClient(null);
        webView.destroy();
        webView = null;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (inCustomView()) {
                hideCustomView();
                return true;
            } else if("http://m.dilidili.wang/".equals(webView.getUrl())){
                exit();
            } else{
                webView.goBack();
            }
        }
        return false;
    }

}
