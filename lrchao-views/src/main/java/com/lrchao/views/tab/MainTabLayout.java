package com.lrchao.views.tab;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Description: 底部MainTab
 *
 * @author lrc19860926@gmail.com
 * @date 2016/10/20 下午5:57
 */

public class MainTabLayout extends LinearLayout implements MainTabItemView.OnMainTabItemViewClickListener {

    private LayoutParams mLayoutParams = new
            LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);

    private OnMainTabItemClickListener mOnMainTabItemClickListener;

    public MainTabLayout(Context context) {
        super(context);
        init(context);
    }

    public MainTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 设置点击事件
     *
     * @param onMainTabItemClickListener OnMainTabItemClickListener
     */
    public void setOnMainTabItemClickListener(OnMainTabItemClickListener onMainTabItemClickListener) {
        mOnMainTabItemClickListener = onMainTabItemClickListener;
    }

    /**
     * @param iconNormal       未选的icon
     * @param iconChecked      选中的icon
     * @param text             文本
     * @param textColorNormal  文本未选的颜色
     * @param textColorChecked 文本选中的颜色
     */
    public void addItem(@DrawableRes int iconNormal,
                        @DrawableRes int iconChecked,
                        @StringRes int text,
                        @ColorRes int textColorNormal,
                        @ColorRes int textColorChecked,
                        @ColorRes int bgColorNormal,
                        @ColorRes int bgColorChecked) {
        MainTabItemView itemView = new MainTabItemView(getContext());
        itemView.bindView(iconNormal,
                iconChecked,
                text,
                textColorNormal,
                textColorChecked,
                bgColorNormal,
                bgColorChecked);
        itemView.setOnMainTabItemClickListener(this);
        addView(itemView, mLayoutParams);
    }

    /**
     * 设置提醒数量
     *
     * @param position 位置
     * @param count    数量
     */
    public void setNoticeCount(int position, int count) {
        MainTabItemView mainTabItemView = (MainTabItemView) getChildAt(position);
        mainTabItemView.setRemindCount(count);
    }

    //============================================
    // private
    //============================================

    /**
     * 设置红点提醒
     */
    public void setRedNoticeShow(int position, boolean isShow) {
        MainTabItemView mainTabItemView = (MainTabItemView) getChildAt(position);
        mainTabItemView.setRemindRedCircle(isShow);
    }

    /**
     * 设置选中的item
     *
     * @param position 位置
     */
    public void clickItem(int position) {
        for (int i = 0; i < getChildCount(); i++) {
            MainTabItemView mainTabItemView = (MainTabItemView) getChildAt(i);
            if (i == position) {
                mainTabItemView.performClick();
            } else {
                mainTabItemView.setChecked(false);
            }
        }
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        setBackgroundResource(android.R.color.white);
    }

    @Override
    public void onMainTabItemViewClick(View view) {

        int clickIndex = 0;

        for (int i = 0; i < getChildCount(); i++) {
            MainTabItemView mainTabItemView = (MainTabItemView) getChildAt(i);
            if (mainTabItemView == view) {
                clickIndex = i;
                mainTabItemView.setChecked(true);
            } else {
                mainTabItemView.setChecked(false);
            }

        }

        if (mOnMainTabItemClickListener != null) {
            mOnMainTabItemClickListener.onMainTabItemClick(clickIndex);
        }
    }

    public interface OnMainTabItemClickListener {
        void onMainTabItemClick(int position);
    }
}
