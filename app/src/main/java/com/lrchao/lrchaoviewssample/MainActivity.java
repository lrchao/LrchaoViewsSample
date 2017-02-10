package com.lrchao.lrchaoviewssample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lrchao.lrchaoviewssample.imageview.ImageViewActivity;
import com.lrchao.lrchaoviewssample.itemview.ItemViewActivity;
import com.lrchao.lrchaoviewssample.tab.MainTabActivity;
import com.lrchao.lrchaoviewssample.toolbar.ToolbarActivity;
import com.lrchao.lrchaoviewssample.webview.WebViewActivity;
import com.lrchao.views.LrchaoViews;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LrchaoViews.getInstance().init(this);
        findViewById(R.id.btn_tab).setOnClickListener(this);
        findViewById(R.id.btn_image_view).setOnClickListener(this);
        findViewById(R.id.btn_toolbar).setOnClickListener(this);
        findViewById(R.id.btn_itemview).setOnClickListener(this);
        findViewById(R.id.btn_webview).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tab:
                startActivity(new Intent(this, MainTabActivity.class));
                break;
            case R.id.btn_image_view:
                startActivity(new Intent(this, ImageViewActivity.class));
                break;
            case R.id.btn_toolbar:
                startActivity(new Intent(this, ToolbarActivity.class));
                break;
            case R.id.btn_itemview:
                startActivity(new Intent(this, ItemViewActivity.class));
                break;
            case R.id.btn_webview:
                startActivity(new Intent(this, WebViewActivity.class));
                break;
            default:
                break;
        }
    }
}
