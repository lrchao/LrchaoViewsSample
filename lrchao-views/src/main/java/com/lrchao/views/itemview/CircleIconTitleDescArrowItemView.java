package com.lrchao.views.itemview;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.text.SpannableStringBuilder;
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
 * Description:
 * 1.左边一个Icon 圆形
 * 2.上面一个Title
 * 3.下面一个描述
 * 4.右边一个未读红点
 * 5.右边一个箭头
 *
 * @author liuranchao
 * @date 16/7/20 上午10:11
 */
public class CircleIconTitleDescArrowItemView extends LinearLayout {

    public MyCircleImageView mIvIcon;

    public TextView mTvTitle;

    public TextView mTvDesc;

    public ImageView mIvIRedPoint;

    public CircleIconTitleDescArrowItemView(Context context) {
        super(context);
        init(context);
    }

    public CircleIconTitleDescArrowItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void bindView(@DrawableRes int drawResId, String title,
                         String desc) {

        mIvIcon.setImageResource(drawResId);
        mTvTitle.setText(title);
        mTvDesc.setText(desc);
    }

    //================================================
    // private
    //================================================

    public void bindView(@DrawableRes int drawResId, SpannableStringBuilder title,
                         String desc) {
        mIvIcon.setImageResource(drawResId);
        mTvTitle.setText(title);
        mTvDesc.setText(desc);
    }

    public void showRedPoint(boolean show) {
        mIvIRedPoint.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.itemview_circle_icon_title_desc, this);
        setOrientation(HORIZONTAL);
        setBackgroundResource(android.R.color.white);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(Utils.dip2px(10), Utils.dip2px(10),
                Utils.dip2px(10), Utils.dip2px(10));

        mIvIcon = (MyCircleImageView) findViewById(R.id.iv_icon);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvDesc = (TextView) findViewById(R.id.tv_desc);
        mIvIRedPoint = (ImageView) findViewById(R.id.iv_red_point);

    }


}
