package com.lrchao.lrchaoviewssample.progressbar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.lrchao.lrchaoviewssample.R;
import com.lrchao.views.progressbar.PageLoadingView;

public class PageLoadingActivity extends AppCompatActivity {

    private static final int MSG_WHAT_SHOW_RESULT = 1;

    private PageLoadingView mPageLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_loading);

        mPageLoadingView = (PageLoadingView) findViewById(R.id.page_loading_view);
        mPageLoadingView.showLoading();
        mPageLoadingView.setOnPageLoadingViewClickListener(new PageLoadingView.OnPageLoadingViewClickListener() {
            @Override
            public void onPageLoadingViewClick() {
                mPageLoadingView.showLoading();
                mHandler.sendEmptyMessageDelayed(MSG_WHAT_SHOW_RESULT, 3000);
            }
        });
        mHandler.sendEmptyMessageDelayed(MSG_WHAT_SHOW_RESULT, 3000);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_WHAT_SHOW_RESULT:
                    mPageLoadingView.show("重新加载", "好的");
                    break;
                default:
                    break;
            }

        }
    };
}
