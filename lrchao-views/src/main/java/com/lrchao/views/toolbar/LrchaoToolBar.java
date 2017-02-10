package com.lrchao.views.toolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lrchao.utils.ResourceUtils;
import com.lrchao.views.R;

/**
 * Description: Toolbar View
 * 需要设置 Theme.AppCompat.Light.NoActionBar
 *
 * @author lrc19860926@gmail.com
 * @date 2016/10/21 下午3:35
 */

public class LrchaoToolBar extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "LrchaoToolBar";

    private Toolbar mToolbar;

    /**
     * 中间的标题
     */
    private TextView mTvCenterTitle;

    /**
     * 返回的图片
     */
    @DrawableRes
    private int mNavigationIcon;

    /**
     * 中间的标题文本
     */
    private CharSequence mCenterTitleText;

    /**
     * 中间的标题字体大小
     */
    private float mCenterTitleSize = 26;

    /**
     * 中间的标题字体颜色
     */
    private int mCenterTitleColor;

    /**
     * 背景色
     */
    private int mBackgroundColor;

    /**
     * 导航接口
     */
    private OnNavigationClickListener mOnNavigationClickListener;

    public LrchaoToolBar(Context context) {
        super(context);
        init(context);
    }

    public LrchaoToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LrchaoToolBar);

        if (a != null) {
            mNavigationIcon = a.getResourceId(R.styleable.LrchaoToolBar_navigation_icon, 0);
            mCenterTitleText = a.getString(R.styleable.LrchaoToolBar_center_title_text);
            mCenterTitleSize = a.getDimensionPixelSize(R.styleable.LrchaoToolBar_center_title_size, 0);
            mCenterTitleColor = a.getResourceId(R.styleable.LrchaoToolBar_center_title_color, android.R.color.black);
            mBackgroundColor = a.getResourceId(R.styleable.LrchaoToolBar_background_color, android.R.color.white);
            a.recycle();
        }
        init(context);
    }

    /**
     * @param listener OnNavigationClickListener
     */
    public void setOnNavigationClickListener(OnNavigationClickListener listener) {
        mOnNavigationClickListener = listener;
    }

    //=============================================
    // private
    //=============================================

    /**
     * 更新标题
     *
     * @param centerTitleText 标题
     */
    public void setCenterTitleText(CharSequence centerTitleText) {
        mCenterTitleText = centerTitleText;
        setupTvCenterTitle();
    }

    /**
     * 更新标题
     *
     * @param centerTitleText 标题
     */
    public void setCenterTitleText(@StringRes int centerTitleText) {
        mCenterTitleText = ResourceUtils.getString(centerTitleText);
        setupTvCenterTitle();
    }

    private void init(Context context) {

        LayoutInflater.from(context).inflate(R.layout.toolbar_lrchao, this);
        setOrientation(HORIZONTAL);
        mTvCenterTitle = (TextView) findViewById(R.id.toolbar_center_title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");

        // Toolbar处理顺序 不能换
        if (context instanceof AppCompatActivity) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) context;
            appCompatActivity.setSupportActionBar(mToolbar);
        }

        if (mNavigationIcon > 0) {
            mToolbar.setNavigationIcon(mNavigationIcon);
            mToolbar.setNavigationOnClickListener(this);
        }

        mToolbar.setBackgroundResource(mBackgroundColor);

        setupTvCenterTitle();
    }

    @Override
    public void onClick(View v) {
        if (mOnNavigationClickListener != null) {
            mOnNavigationClickListener.onNavigationClick();
        }
    }

    private void setupTvCenterTitle() {
        mTvCenterTitle.setText(mCenterTitleText);
        mTvCenterTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, mCenterTitleSize);
        mTvCenterTitle.setTextColor(ResourceUtils.getColor(mCenterTitleColor));
    }

}
