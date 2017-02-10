package com.lrchao.views.tab;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lrchao.utils.ResourceUtils;
import com.lrchao.views.R;


/**
 * Description: 主页面的Main Tab的 单个View
 * icon selector 要有 state_checked
 *
 * @author liuranchao
 * @date 16/3/13 上午11:07
 */
public class MainTabItemView extends RelativeLayout implements View.OnClickListener {

    /**
     * 图标
     */
    private ImageView mIvIcon;

    /**
     * 名称
     */
    private TextView mTvName;

    /**
     * 提醒数量
     */
    private TextView mTvRemindCount;

    /**
     * 只是红点
     */
    private ImageView mIvNotice;

    /**
     * 正常的icon
     */
    private int mIconNormal;
    /**
     * 选中的icon
     */
    private int mIconChecked;

    /**
     * 未选的文字颜色
     */
    private int mTextColorNormal;

    /**
     * 选中的文字颜色
     */
    private int mTextColorChecked;

    /**
     * 监听接口
     */
    private OnMainTabItemViewClickListener mOnMainTabItemClickListener;


    public MainTabItemView(Context context) {
        super(context);
        init(context);
    }

    public MainTabItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 设置点击监听
     *
     * @param onMainTabItemClickListener OnMainTabItemViewClickListener
     */
    public void setOnMainTabItemClickListener(OnMainTabItemViewClickListener onMainTabItemClickListener) {
        mOnMainTabItemClickListener = onMainTabItemClickListener;
    }

    /**
     * 绑定view
     */
    public void bindView(@DrawableRes int iconNormal,
                         @DrawableRes int iconChecked,
                         @StringRes int text,
                         @ColorRes int textColorNormal,
                         @ColorRes int textColorChecked) {
        mIvIcon.setImageResource(iconNormal);
        mTvName.setText(text);
        mTvName.setTextColor(ResourceUtils.getColor(textColorNormal));
        mIconNormal = iconNormal;
        mIconChecked = iconChecked;
        mTextColorNormal = textColorNormal;
        mTextColorChecked = textColorChecked;
    }

    /**
     * 设置提醒的view
     *
     * @param count 提醒的数量
     */
    public void setRemindCount(int count) {
        if (count > 0) {
            mTvRemindCount.setVisibility(View.VISIBLE);
            mTvRemindCount.setText(String.valueOf(count));


        } else {
            mTvRemindCount.setVisibility(View.GONE);
        }

    }


    //=========================================
    // private
    //=========================================

    /**
     * 设置红点
     */
    public void setRemindRedCircle(boolean isShow) {
        if (isShow) {
            mIvNotice.setVisibility(View.VISIBLE);

            setRemindCount(0);
        } else {
            mIvNotice.setVisibility(View.GONE);
        }
    }

    /**
     * 设置view选中
     *
     * @param checked 是否选中
     */
    public void setChecked(boolean checked) {
        if (checked) {
            checkStatus();
        } else {
            uncheckedStatus();
        }
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.tab_main_item, this);
        setBackgroundResource(android.R.color.white);
        mIvIcon = (ImageView) findViewById(R.id.iv_tab_img);
        mTvName = (TextView) findViewById(R.id.tv_tab_text);
        mTvRemindCount = (TextView) findViewById(R.id.tv_tab_notice_count);
        mIvNotice = (ImageView) findViewById(R.id.iv_notice);
        setOnClickListener(this);

        setRemindRedCircle(false);
        setRemindCount(0);
    }

    @Override
    public void onClick(View v) {
        if (mOnMainTabItemClickListener != null) {
            mOnMainTabItemClickListener.onMainTabItemViewClick(this);
        }
    }

    private void checkStatus() {
        mIvIcon.setImageResource(mIconChecked);
        mTvName.setTextColor(ResourceUtils.getColor(mTextColorChecked));
    }

    public void uncheckedStatus() {
        mIvIcon.setImageResource(mIconNormal);
        mTvName.setTextColor(ResourceUtils.getColor(mTextColorNormal));
    }

    public interface OnMainTabItemViewClickListener {
        void onMainTabItemViewClick(View view);
    }

}
