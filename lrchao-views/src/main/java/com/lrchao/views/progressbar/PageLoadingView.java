package com.lrchao.views.progressbar;

import android.content.Context;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.lrchao.views.R;


/**
 * Description:页面加载等待、显示结果的界面
 *
 * @author liuranchao
 */
public class PageLoadingView extends RelativeLayout implements
        View.OnClickListener, PageLoadingResultView.OnPageLoadingResultViewClickListener {

    private Context mContext;
    /**
     * 加载结果view
     */
    private PageLoadingResultView mPageLoadingResultView;

    /**
     * 加载中的
     */
    private ProgressBar mPbLoading;

    private ViewGroup mCustomContainerView;

    /**
     * 点击
     */
    private OnPageLoadingViewClickListener mOnPageLoadingViewClickListener;

    public PageLoadingView(Context context) {
        super(context);
        initView(context);
    }

    public PageLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void setOnPageLoadingViewClickListener(OnPageLoadingViewClickListener onPageLoadingViewClickListener) {
        mOnPageLoadingViewClickListener = onPageLoadingViewClickListener;
    }

    private void initView(Context context) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_page_loading, this);
        setBackgroundResource(android.R.color.white);
        mPageLoadingResultView = (PageLoadingResultView) findViewById(R.id.rsv_result_view);

        mPbLoading = (ProgressBar) findViewById(R.id.pb_loading_view);

        mPageLoadingResultView.setOnPageLoadingResultViewClickListener(this);
        mCustomContainerView = (ViewGroup) findViewById(R.id.ll_custom_page_container);
        setOnClickListener(this);
    }

    /**
     * 显示加载中的
     */
    public void showLoading() {
        mPbLoading.setVisibility(View.VISIBLE);
        mPageLoadingResultView.setVisibility(View.GONE);
        mCustomContainerView.setVisibility(View.GONE);
        setVisibility(View.VISIBLE);

    }

    /**
     * 隐藏page loading view
     */
    public void dismiss() {
        setVisibility(View.GONE);
    }

    /**
     * 显示
     */
    public void show(@StringRes int tvResId, @StringRes int btnResId) {
        show(mContext.getString(tvResId), mContext.getString(btnResId));
    }

    public void show(String tvText, String btnText) {
        mPbLoading.setVisibility(View.GONE);
        mPageLoadingResultView.setVisibility(View.VISIBLE);
        mCustomContainerView.setVisibility(View.GONE);
        mPageLoadingResultView.show(tvText, btnText);
        setVisibility(View.VISIBLE);
    }


    public void setCustomResultView(View customPageView) {

        mPbLoading.setVisibility(View.GONE);
        mPageLoadingResultView.setVisibility(View.GONE);
        mCustomContainerView.setVisibility(View.VISIBLE);
        mCustomContainerView.removeAllViews();
        mCustomContainerView.addView(customPageView);
        setVisibility(View.VISIBLE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                if (mPbLoading.getVisibility() != View.VISIBLE &&
                        mCustomContainerView.getVisibility() != View.VISIBLE) {
                    onPageLoadingViewClick();
                }
                break;
        }
    }

    @Override
    public void onPageLoadingResultViewClick() {
        onPageLoadingViewClick();
    }

    private void onPageLoadingViewClick() {
        if (mOnPageLoadingViewClickListener != null) {
            mOnPageLoadingViewClickListener.onPageLoadingViewClick();
        }
    }


    public interface OnPageLoadingViewClickListener {
        void onPageLoadingViewClick();
    }
}
