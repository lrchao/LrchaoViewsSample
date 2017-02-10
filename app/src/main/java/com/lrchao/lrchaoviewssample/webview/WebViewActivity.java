package com.lrchao.lrchaoviewssample.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lrchao.lrchaoviewssample.R;
import com.lrchao.views.webview.MyWebView;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        MyWebView myWebView = (MyWebView) findViewById(R.id.mywebview);

        myWebView.setUrl("https://www.baidu.com/");
        myWebView.load();
    }
}
