package com.lrchao.views.itemview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lrchao.views.R;

import static com.lrchao.utils.DensityUtils.dip2px;


/**
 * Description:
 * 1.左边图标
 * 2.左边文字
 * 3.右边箭头
 *
 * @author lrc19860926@gmail.com
 * @date 2016/10/28 下午2:07
 */

public final class IconTextItemLayout extends LinearLayout implements View.OnClickListener {

    public TextView mTvTitle;

    public ImageView mIvIcon;

    private OnIconTextItemLayoutClickListener mOnIconTextItemLayoutClickListener;

    public IconTextItemLayout(Context context) {
        super(context);
        init(context);
    }

    public IconTextItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 设置点击监听
     */
    public void setOnIconTextItemLayoutClickListener(OnIconTextItemLayoutClickListener
                                                             onIconTextItemLayoutClickListener) {
        mOnIconTextItemLayoutClickListener = onIconTextItemLayoutClickListener;
    }

    //======================================================

    /**
     * 设置内边距
     * dp
     */
    public void setCustomPadding(int left, int top, int right, int bottom) {
        setPadding(dip2px(left), dip2px(top), dip2px(right), dip2px(bottom));
    }

    /**
     * 设置IconView大小
     *
     * @param size dp
     */
    public void setIconSize(int size) {
        LayoutParams linearParams = (LayoutParams) mIvIcon.getLayoutParams();
        linearParams.width = dip2px(size);
        linearParams.height = dip2px(size);
        mIvIcon.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件aaa
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.itemview_icon_text, this);
        setOrientation(HORIZONTAL);
        setBackgroundResource(android.R.color.white);
        int paddingValue = dip2px(10);
        setPadding(paddingValue, paddingValue, paddingValue, paddingValue);
        setGravity(Gravity.CENTER_VERTICAL);

        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mIvIcon = (ImageView) findViewById(R.id.iv_icon);

        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mOnIconTextItemLayoutClickListener != null) {
            mOnIconTextItemLayoutClickListener.onIconTextItemLayoutClick();
        }
    }

    public interface OnIconTextItemLayoutClickListener {
        void onIconTextItemLayoutClick();
    }
}
