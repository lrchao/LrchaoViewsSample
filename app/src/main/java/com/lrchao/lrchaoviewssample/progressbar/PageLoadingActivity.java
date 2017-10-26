package com.lrchao.lrchaoviewssample.progressbar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.lrchao.lrchaoviewssample.R;
import com.lrchao.views.progressbar.PageLoadingView;

public class PageLoadingActivity extends AppCompatActivity {

    private static final int MSG_WHAT_SHOW_RESULT = 1;
    private static final int MSG_WHAT_SHOW_RESULT_1 = 2;

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
                    View v1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_empty_1, null);
                    v1.findViewById(R.id.btn_tab).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mHandler.sendEmptyMessageDelayed(MSG_WHAT_SHOW_RESULT_1, 3000);
                        }
                    });
                    mPageLoadingView.setCustomResultView(v1);
                    break;

                case MSG_WHAT_SHOW_RESULT_1:
                    View v2 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_empty_2, null);
                    v2.findViewById(R.id.btn_tab_1).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mHandler.sendEmptyMessageDelayed(MSG_WHAT_SHOW_RESULT, 3000);
                        }
                    });
                    mPageLoadingView.setCustomResultView(v2);
                    break;
                default:
                    break;
            }

        }
    };
}
