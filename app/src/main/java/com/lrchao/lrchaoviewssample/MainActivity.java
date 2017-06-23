package com.lrchao.lrchaoviewssample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lrchao.lrchaoviewssample.edittext.TextInputLayoutActivity;
import com.lrchao.lrchaoviewssample.gridview.GridViewActivity;
import com.lrchao.lrchaoviewssample.imageview.ImageViewActivity;
import com.lrchao.lrchaoviewssample.itemview.ItemViewActivity;
import com.lrchao.lrchaoviewssample.photo.PhotoActivity;
import com.lrchao.lrchaoviewssample.popupwindow.PopupWindowActivity;
import com.lrchao.lrchaoviewssample.progressbar.PageLoadingActivity;
import com.lrchao.lrchaoviewssample.progressbar.ProgressbarActivity;
import com.lrchao.lrchaoviewssample.swipe.SwipeRefreshActivity;
import com.lrchao.lrchaoviewssample.tab.MainTabActivity;
import com.lrchao.lrchaoviewssample.textview.TextViewActivity;
import com.lrchao.lrchaoviewssample.toolbar.ToolbarActivity;
import com.lrchao.lrchaoviewssample.webview.WebViewActivity;
import com.lrchao.utils.LrchaoUtils;
import com.lrchao.views.LrchaoViews;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LrchaoUtils.getInstance().init(this);
        LrchaoViews.getInstance().init(this);
        findViewById(R.id.btn_tab).setOnClickListener(this);
        findViewById(R.id.btn_image_view).setOnClickListener(this);
        findViewById(R.id.btn_toolbar).setOnClickListener(this);
        findViewById(R.id.btn_itemview).setOnClickListener(this);
        findViewById(R.id.btn_webview).setOnClickListener(this);
        findViewById(R.id.btn_progressbar).setOnClickListener(this);
        findViewById(R.id.btn_page_loading).setOnClickListener(this);
        findViewById(R.id.btn_swipe_refresh).setOnClickListener(this);
        findViewById(R.id.btn_text_view).setOnClickListener(this);
        findViewById(R.id.btn_grid_view).setOnClickListener(this);
        findViewById(R.id.btn_popupwindow).setOnClickListener(this);
        findViewById(R.id.btn_photo).setOnClickListener(this);
        findViewById(R.id.btn_text_input_layout).setOnClickListener(this);

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
            case R.id.btn_progressbar:
                startActivity(new Intent(this, ProgressbarActivity.class));
                break;
            case R.id.btn_page_loading:
                startActivity(new Intent(this, PageLoadingActivity.class));
                break;
            case R.id.btn_swipe_refresh:
                startActivity(new Intent(this, SwipeRefreshActivity.class));
                break;
            case R.id.btn_text_view:
                startActivity(new Intent(this, TextViewActivity.class));
                break;
            case R.id.btn_grid_view:
                startActivity(new Intent(this, GridViewActivity.class));
                break;
            case R.id.btn_popupwindow:
                startActivity(new Intent(this, PopupWindowActivity.class));
                break;
            case R.id.btn_photo:
                startActivity(new Intent(this, PhotoActivity.class));
                break;
            case R.id.btn_text_input_layout:
                startActivity(new Intent(this, TextInputLayoutActivity.class));
                break;

            default:
                break;
        }
    }
}
