package com.lrchao.views.progressbar;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lrchao.views.R;


/**
 * Description: Page Loading View's text
 * and button of center area
 *
 * @author liuranchao
 */
public class PageLoadingResultView extends LinearLayout
        implements View.OnClickListener {

    /**
     * 按钮上的文本
     */
    private TextView mTvResult;

    /**
     * 点击的按钮
     */
    private Button mBtnResult;

    private Context mContext;

    /**
     * 点击回调接口
     */
    private OnPageLoadingResultViewClickListener mOnPageLoadingResultViewClickListener;

    public PageLoadingResultView(Context context) {
        super(context);
        initView(context);
    }

    public PageLoadingResultView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void setOnPageLoadingResultViewClickListener(OnPageLoadingResultViewClickListener
                                                                onPageLoadingResultViewClickListener) {
        mOnPageLoadingResultViewClickListener = onPageLoadingResultViewClickListener;
    }

    private void initView(Context context) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_page_loading_result, this);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        mTvResult = (TextView) findViewById(R.id.tv_result);
        mBtnResult = (Button) findViewById(R.id.btn_result);
        mTvResult.setOnClickListener(this);
        mBtnResult.setOnClickListener(this);
    }

    public void show(int tvResId, int btnResId) {
        show(mContext.getString(tvResId), mContext.getString(btnResId));
    }

    public void show(String tvText, String btnText) {
        if (TextUtils.isEmpty(tvText)) {
            mTvResult.setVisibility(View.GONE);
        } else {
            mTvResult.setText(tvText);
            mTvResult.setVisibility(View.VISIBLE);
        }

        if (TextUtils.isEmpty(btnText)) {
            mBtnResult.setVisibility(View.GONE);
        } else {
            mBtnResult.setText(btnText);
            mBtnResult.setVisibility(View.VISIBLE);
        }


        setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_result || i == R.id.tv_result) {
            if (mOnPageLoadingResultViewClickListener != null) {
                mOnPageLoadingResultViewClickListener.onPageLoadingResultViewClick();
            }

        }
    }

    public interface OnPageLoadingResultViewClickListener {
        void onPageLoadingResultViewClick();
    }
}
