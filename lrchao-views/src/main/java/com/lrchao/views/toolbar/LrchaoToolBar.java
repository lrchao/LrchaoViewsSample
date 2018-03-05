package com.lrchao.views.toolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
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

import com.lrchao.views.R;
import com.lrchao.views.Utils;

/**
 * Description: Toolbar View
 * 需要设置 Theme.AppCompat.Light.NoActionBar
 *
 * @author lrc19860926@gmail.com
 * @date 2016/10/21 下午3:35
 */

public class LrchaoToolBar extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "LrchaoToolBar";

    private Context mContext;

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
     * 标题的样式
     */
    private int mTitleTextAppearance;

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
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LrchaoToolBar);

        if (a != null) {
            mNavigationIcon = a.getResourceId(R.styleable.LrchaoToolBar_navigation_icon, 0);
            mCenterTitleText = a.getString(R.styleable.LrchaoToolBar_center_title_text);
            mCenterTitleSize = a.getDimensionPixelSize(R.styleable.LrchaoToolBar_center_title_size, 0);
            mCenterTitleColor = a.getResourceId(R.styleable.LrchaoToolBar_center_title_color, android.R.color.black);
            mBackgroundColor = a.getResourceId(R.styleable.LrchaoToolBar_background_color, android.R.color.white);
            mTitleTextAppearance = a.getResourceId(R.styleable.LrchaoToolBar_title_text_appearance, 0);
            a.recycle();
        }
        init(context);
    }

    /**
     *  Toolbar的标题颜色,和setTitleTextAppearance冲突，并setTitleTextAppearance为准
     */
    public void setTitleTextColor(int color) {
        mToolbar.setTitleTextColor(color);
    }

    /**
     * 设置标题
     */
    public void setTitle(String s) {
        mToolbar.setTitle(s);
    }

    /**
     *  设置title样式
     */
    public void  setTitleTextAppearance(int resId) {
        mToolbar.setTitleTextAppearance(mContext, resId);
    }

    /**
     * 设置导航的图标
     *
     * @param drawable 图标
     */
    public void setNavigationIcon(@DrawableRes int drawable) {
        if (drawable > 0) {
            mNavigationIcon = drawable;
            mToolbar.setNavigationIcon(mNavigationIcon);
            mToolbar.setNavigationOnClickListener(this);
        } else {
            mToolbar.setNavigationIcon(android.R.color.transparent);
            mToolbar.setNavigationOnClickListener(null);
        }
    }

    /**
     * @param listener OnNavigationClickListener
     */
    public void setOnNavigationClickListener(OnNavigationClickListener listener) {
        mOnNavigationClickListener = listener;
    }

    /**
     * 设置背景
     */
    public void setBg(@ColorRes  int colorRes) {
        mBackgroundColor = colorRes;
        mToolbar.setBackgroundResource(mBackgroundColor);
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
        mCenterTitleText = Utils.getString(centerTitleText);
        setupTvCenterTitle();
    }

    /**
     * 更新标题的颜色
     */
    public void setCenterTitleColor(@ColorRes int color) {
        mCenterTitleColor = color /*= ResourceUtils.getColor(color)*/;
        setupTvCenterTitle();
    }

    private void init(Context context) {

        LayoutInflater.from(context).inflate(R.layout.toolbar_lrchao, this);
        setOrientation(HORIZONTAL);
        mTvCenterTitle = (TextView) findViewById(R.id.toolbar_center_title);
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        mToolbar.setTitle("");

        // Toolbar处理顺序 不能换
        if (context instanceof AppCompatActivity) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) context;
            appCompatActivity.setSupportActionBar(mToolbar);
        }

        setNavigationIcon(mNavigationIcon);

        mToolbar.setBackgroundResource(mBackgroundColor);
        mToolbar.setTitleTextAppearance(context, mTitleTextAppearance);
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
        mTvCenterTitle.setTextColor(Utils.getColor(mCenterTitleColor));
    }

}
