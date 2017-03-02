package com.lrchao.views.itemview;

import android.content.Context;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lrchao.views.R;
import com.lrchao.views.Utils;
import com.lrchao.views.imageview.MyCircleImageView;


/**
 * Description: 单行，标题 图片 箭头的布局
 *
 * @author liuranchao
 * @date 16/4/14 下午7:55
 */
public class EditIconItemLayout extends LinearLayout implements View.OnClickListener {

    /**
     * 标题
     */
    public TextView mTvTitle;

    /**
     * 图片内容
     */
    public MyCircleImageView mIvIcon;

    /**
     * 箭头
     */
    public ImageView mIvArrow;

    /**
     * 头像点击监听
     */
    private OnIconClickListener mOnIconClickListener;

    public EditIconItemLayout(Context context) {
        super(context);
        init(context);
    }

    public EditIconItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 设置标题
     *
     * @param strId strings.xml
     */
    public void setTitle(@StringRes int strId) {
        mTvTitle.setText(strId);
    }

    /**
     * 设置箭头
     *
     * @param isShow 是否显示箭头
     */
    public void setShowArrow(boolean isShow) {
        if (isShow) {
            mIvArrow.setVisibility(View.VISIBLE);
        } else {
            mIvArrow.setVisibility(View.INVISIBLE);
        }
    }

    //================================================
    // private
    //================================================

    public void setOnIconClickListener(OnIconClickListener onIconClickListener) {
        mOnIconClickListener = onIconClickListener;
    }

    public void clickIconView() {
        if (mOnIconClickListener != null) {
            mOnIconClickListener.onIconClick();
        }
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.itemview_edit_icon, this);

        setOrientation(HORIZONTAL);
        setBackgroundResource(android.R.color.white);
        int paddingTopBottomValue = Utils.dip2px(10);
        int paddingLeftRightValue = Utils.dip2px(15);
        setPadding(paddingLeftRightValue, paddingTopBottomValue, paddingLeftRightValue, paddingTopBottomValue);
        setGravity(Gravity.CENTER_VERTICAL);

        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mIvIcon = (MyCircleImageView) findViewById(R.id.iv_icon);
        mIvIcon.setOnClickListener(this);
        mIvArrow = (ImageView) findViewById(R.id.iv_arrow_right);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_icon) {
            clickIconView();
        } else {
        }
    }

    /**
     * 头像点击的监听器
     */
    public interface OnIconClickListener {
        void onIconClick();
    }

}
