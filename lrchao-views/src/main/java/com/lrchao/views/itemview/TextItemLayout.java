package com.lrchao.views.itemview;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lrchao.views.R;
import com.lrchao.views.Utils;

import static com.lrchao.views.Utils.dip2px;


/**
 * Description: 单行，标题 内容 箭头的布局
 *
 * @author liuranchao
 * @date 16/4/14 下午2:09
 */
public class TextItemLayout extends LinearLayout {

    public static final int ALIGNING_LEFT = 0;
    public static final int ALIGNING_RIGHT = 1;

    /**
     * 标题
     */
    TextView mTvTitle;

    /**
     * 内容
     */
    TextView mTvContent;

    /**
     * hint 内容的View
     */
    TextView mTvHint;

    /**
     * 箭头
     */
    ImageView mIvArrow;

    /**
     * hint的文本
     */
    private CharSequence mHintText;

    public TextItemLayout(Context context) {
        super(context);
        init(context);
    }

    public TextItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.itemview_text, this);
        setOrientation(HORIZONTAL);
        setBackgroundResource(android.R.color.white);
        int paddingValue = dip2px(15);
        setPadding(paddingValue, paddingValue, paddingValue, paddingValue);
        setGravity(Gravity.CENTER_VERTICAL);

        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mTvHint = (TextView) findViewById(R.id.tv_hint);
        mIvArrow = (ImageView) findViewById(R.id.iv_arrow_right);
    }

    /**
     * 设置标题的字体大小
     *
     * @param textSize sp
     */
    public void setTitleSize(int textSize) {
        if (mTvTitle != null) {
            mTvTitle.setTextSize(textSize);
        }
    }

    /**
     * 设置内容的方向
     *
     * @param aligning {@link #ALIGNING_LEFT} {@link #ALIGNING_RIGHT}
     * @param margin   外边距
     */
    public void setHintAligning(int aligning, int margin) {

        if (aligning == ALIGNING_LEFT) {
            setContentAligning(mTvHint, mTvTitle);
            setLeftMargin(mTvHint, margin);
        } else {
            setContentAligning(mTvTitle, mTvContent);
            setRightMargin(mTvHint, margin);
        }
    }

    /**
     * 设置hint文本
     *
     * @param hintTextResId strings.xml
     */
    public void setHintText(@StringRes int hintTextResId) {
        mHintText = Utils.getString(hintTextResId);
        showHintView();
    }

    /**
     * 设置hint文本
     *
     * @param hintText 提示的文本
     */
    public void setHintText(CharSequence hintText) {
        mHintText = hintText;
        showHintView();
    }

    /**
     * 设置文本内容
     *
     * @param content 文本内容
     */
    public void setContent(String content) {
        if (!TextUtils.isEmpty(content)) {
            showContentView(content);
        } else {
            showHintView();
        }
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
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(CharSequence title) {
        mTvTitle.setText(title);
    }


    /**
     * 设置标题
     * Html方式
     *
     * @param title 标题
     */
    public void setTitle(Spanned title) {
        mTvTitle.setText(title);
    }

    /**
     * 设置标题的颜色
     */
    public void setTitleColor(@ColorRes int color) {
        if (mTvTitle != null) {
            mTvTitle.setTextColor(Utils.getColor(color));
        }
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


    /**
     * 获取内容的文本
     */
    public String getContentText() {
        return mTvContent.getText().toString().trim();
    }

    /**
     * 设置行数
     *
     * @param lines 行数
     */
    public void setTitleLines(int lines) {
        mTvTitle.setLines(lines);
    }

    /**
     * {@link TextUtils.TruncateAt#END}
     *
     * @param where TextUtils.TruncateAt
     */
    public void setTitleEllipsize(TextUtils.TruncateAt where) {
        mTvTitle.setEllipsize(where);
    }

    //======================================================
    // private
    //======================================================

    private void showHintView() {
        mTvHint.setText(mHintText);
        mTvHint.setVisibility(View.VISIBLE);
        mTvContent.setVisibility(View.GONE);
    }

    private void showContentView(String content) {
        mTvContent.setText(content);
        mTvContent.setVisibility(View.VISIBLE);
        mTvHint.setVisibility(View.GONE);
    }

    private void setContentAligning(TextView maxWeightView, TextView minWeiightView) {
        if (maxWeightView != null) {
            LayoutParams params = (LayoutParams) maxWeightView.getLayoutParams();
            params.weight = 1;
            maxWeightView.setLayoutParams(params);
        }

        if (minWeiightView != null) {
            LayoutParams params = (LayoutParams) minWeiightView.getLayoutParams();
            params.weight = 0;
            minWeiightView.setLayoutParams(params);
        }
    }

    private void setLeftMargin(View view, int margin) {
        if (view != null) {
            LayoutParams params = (LayoutParams) view.getLayoutParams();
            params.setMargins(Utils.dip2px(margin), 0, 0, 0);
            view.setLayoutParams(params);

        }
    }

    private void setRightMargin(View view, int margin) {
        if (view != null) {
            LayoutParams params = (LayoutParams) view.getLayoutParams();
            params.setMargins(0, 0, Utils.dip2px(margin), 0);
            view.setLayoutParams(params);

        }
    }
}
