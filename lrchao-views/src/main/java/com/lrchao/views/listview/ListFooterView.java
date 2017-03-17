package com.lrchao.views.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lrchao.views.R;

import static com.lrchao.views.R.id.tv_footer;


/**
 * Description: RecycleView的footer view
 *
 * @author liuranchao
 * @date 16/2/29 下午1:25
 */
public class ListFooterView extends RelativeLayout implements View.OnClickListener {

    /**
     * Loading
     */
    private ProgressBar mProgressBar;

    /**
     * 没有更多数据
     */
    private TextView mTvContent;

    private boolean mIsClickable;

    private OnFooterClickListener mOnFooterClickListener;

    public void setOnFooterClickListener(OnFooterClickListener onFooterClickListener) {
        mOnFooterClickListener = onFooterClickListener;
    }

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
        setOnClickListener(this);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_footer);
        mTvContent = (TextView) findViewById(tv_footer);
    }

    private String mNoMoreDataText;
    private String mErrorText;

    public void setNoMoreDataText(String text) {
        mNoMoreDataText = text;

    }

    public void setErrorText(String text) {
        mErrorText = text;
    }

    /**
     * 显示没有更多数据
     */
    public void showNoMoreData() {
        mIsClickable = true;
        mProgressBar.setVisibility(View.GONE);
        mTvContent.setVisibility(View.VISIBLE);
        mTvContent.setText(mNoMoreDataText);
    }

    /**
     * 设置失败的显示
     */
    public void showError() {
        mIsClickable = true;
        mProgressBar.setVisibility(View.GONE);
        mTvContent.setVisibility(View.VISIBLE);
        mTvContent.setText(mErrorText);
    }

    /**
     * 显示正在加载中
     */
    public void showLoading() {
        mIsClickable = false;
        mProgressBar.setVisibility(View.VISIBLE);
        mTvContent.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {

        if (mOnFooterClickListener != null && mIsClickable) {
            mOnFooterClickListener.onFooterClick();
        }

    }

    public interface OnFooterClickListener {
        void onFooterClick();
    }
}
