package com.lrchao.views.itemview;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lrchao.views.R;
import com.lrchao.views.Utils;
import com.lrchao.views.imageview.MyCircleImageView;

/**
 * Description: 类似聊天列表的Item布局
 * 1.左边一个Icon
 * 2.上面一个Title
 * 3.下面一个描述
 * 4.右边一个未读数量
 *
 * @author lrc19860926@gmail.com
 * @date 2016/10/19 下午1:50
 */

public class ConversationItemView extends LinearLayout {

    public MyCircleImageView mIvIcon;

    public TextView mTvTitle;

    public TextView mTvDesc;

    public TextView mTvUnreadNum;

    public ConversationItemView(Context context) {
        super(context);
        init(context);
    }

    public ConversationItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void bindView(@DrawableRes int drawResId, SpannableStringBuilder title,
                         String desc) {
        mIvIcon.setImageResource(drawResId);
        mTvTitle.setText(title);
        mTvDesc.setText(desc);
    }

    public void bindView(String title,
                         String desc, int unreadNum) {

        mTvTitle.setText(title);
        mTvDesc.setText(desc);

        setUnreadNum(unreadNum);
    }

    public void setUnreadNum(int num) {
        if (num <= 0) {
            mTvUnreadNum.setVisibility(View.GONE);
        } else {
            mTvUnreadNum.setText(String.valueOf(num));
            mTvUnreadNum.setVisibility(View.VISIBLE);
        }
    }

    //================================================
    // private
    //================================================

    public CharSequence getTitleText() {
        return mTvTitle.getText();
    }

    public CharSequence getDescText() {
        return mTvDesc.getText();
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.itemview_conversation, this);
        setOrientation(HORIZONTAL);
        setBackgroundResource(android.R.color.white);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(Utils.dip2px(10), Utils.dip2px(10),
                Utils.dip2px(10), Utils.dip2px(10));

        mIvIcon = (MyCircleImageView) findViewById(R.id.iv_icon);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvDesc = (TextView) findViewById(R.id.tv_desc);
        mTvUnreadNum = (TextView) findViewById(R.id.tv_unread_number);
        mTvUnreadNum.setVisibility(View.GONE);
    }

}
