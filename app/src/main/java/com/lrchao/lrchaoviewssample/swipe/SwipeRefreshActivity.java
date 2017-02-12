package com.lrchao.lrchaoviewssample.swipe;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import com.lrchao.lrchaoviewssample.R;
import com.lrchao.views.swipe.CustomSwipeRefreshLayout;

public class SwipeRefreshActivity extends AppCompatActivity {

    private static final int MSG_WHAT_REFRESH_FINISH = 1;

    private CustomSwipeRefreshLayout mCustomSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);

        mCustomSwipeRefreshLayout = (CustomSwipeRefreshLayout) findViewById(R.id.swipe_refresh);

        mCustomSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(MSG_WHAT_REFRESH_FINISH, 3000);
            }
        });

    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_WHAT_REFRESH_FINISH) {
                mCustomSwipeRefreshLayout.setRefreshing(false);
            }
        }
    };
}
