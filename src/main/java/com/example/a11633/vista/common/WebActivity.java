package com.example.a11633.vista.common;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.a11633.vista.R;

/**
 * 用于网络页面显示的活动
 * */
public class WebActivity extends BaseActivity {
    private static final String param1 = "url";
    public static void startActivity(Context context,String url){
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(param1,url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        String url = getIntent().getStringExtra(param1);
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);//允许使用js动态效果
        webView.setWebViewClient(new WebViewClient());//当跳转到别的页面是仍然使用webview
        webView.loadUrl(url);
    }

}
