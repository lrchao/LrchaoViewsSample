package com.lrchao.views.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lrchao.views.R;


/**
 * Description: RecycleView的footer view
 *
 * @author liuranchao
 * @date 16/2/29 下午1:25
 */
public class ListFooterView extends RelativeLayout {

    /**
     * Loading
     */
    private ProgressBar mProgressBar;

    /**
     * 没有更多数据
     */
    private TextView mTvContent;

    public ListFooterView(Context context) {
        super(context);
        init(context);
    }

    public ListFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.list_footer, this);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_footer);
        mTvContent = (TextView) findViewById(R.id.tv_footer);
    }

    /**
     * 显示没有更多数据
     */
    public void showNoMoreData() {
        mProgressBar.setVisibility(View.GONE);
        mTvContent.setVisibility(View.VISIBLE);
    }

    /**
     * 显示正在加载中
     */
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
        mTvContent.setVisibility(View.GONE);
    }
}
