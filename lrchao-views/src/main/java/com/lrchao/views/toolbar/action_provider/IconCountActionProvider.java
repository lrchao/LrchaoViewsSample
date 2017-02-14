package com.lrchao.views.toolbar.action_provider;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.view.ActionProvider;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lrchao.views.R;

/**
 * Description: 图标和未读数字的view
 *
 * @author lrc19860926@gmail.com
 * @date 2016/10/21 下午5:29
 */

public class IconCountActionProvider extends ActionProvider {

    /**
     * 图标
     */
    private ImageView mIvIcon;

    /**
     * 未读数量
     */
    private TextView mTvCount;

    /**
     * 文字
     */
    private TextView mTvText;

    /**
     * 传入进来，为了区分点击标示的
     */
    private int mClickWhat;

    /**
     * 点击事件
     */
    private OnIconCountActionProviderClickListener mOnClickListener;
    private View.OnClickListener mOnViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnClickListener != null)
                mOnClickListener.onIconCountActionProviderClick(mClickWhat);
        }
    };

    /**
     * Creates a new instance.
     *
     * @param context Context for accessing resources.
     */
    public IconCountActionProvider(Context context) {
        super(context);
    }

    /**
     * 设置图标。
     *
     * @param icon drawable 或者mipmap中的id。
     */
    public void setIcon(@DrawableRes int icon) {
        mIvIcon.setImageResource(icon);
        mIvIcon.setVisibility(View.VISIBLE);
        mTvText.setVisibility(View.GONE);
    }

    public void setText(@StringRes int text) {
        mTvText.setText(text);
        mTvText.setVisibility(View.VISIBLE);
        mIvIcon.setVisibility(View.GONE);
    }

    public void setText(String text) {
        if (TextUtils.isEmpty(text)) {
            mTvText.setVisibility(View.GONE);
        } else {
            mTvText.setText(text);
            mTvText.setVisibility(View.VISIBLE);
        }
        mIvIcon.setVisibility(View.GONE);
    }

    //================================================
    // private
    //================================================

    /**
     * 设置显示的数字。
     *
     * @param i 数字。
     */
    public void setCount(int i) {
        if (i > 0) {
            mTvCount.setText(Integer.toString(i));
            mTvCount.setVisibility(View.VISIBLE);
        } else {
            mTvCount.setVisibility(View.GONE);
        }
    }

    /**
     * @param drawable 数字的背景
     */
    public void setCountTextBackground(@DrawableRes int drawable) {
        mTvCount.setBackgroundResource(drawable);
    }

    @Override
    public View onCreateActionView() {
        int size = getContext().getResources().getDimensionPixelSize(R.dimen.abc_action_bar_default_height_material);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(size, size);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.toolbar_action_count, null, false);
        view.setLayoutParams(layoutParams);
        mIvIcon = (ImageView) view.findViewById(R.id.iv_toolbar_action_icon);
        mTvCount = (TextView) view.findViewById(R.id.tv_toolbar_action_count);
        mTvText = (TextView) view.findViewById(R.id.tv_toolbar_action);
        view.setOnClickListener(mOnViewClickListener);

        setCount(0);
        return view;
    }

    /**
     * 设置点击监听。
     *
     * @param what            what。
     * @param onClickListener listener。
     */
    public void setOnClickListener(int what, OnIconCountActionProviderClickListener onClickListener) {
        this.mClickWhat = what;
        this.mOnClickListener = onClickListener;
    }

    public interface OnIconCountActionProviderClickListener {
        void onIconCountActionProviderClick(int what);
    }

}
