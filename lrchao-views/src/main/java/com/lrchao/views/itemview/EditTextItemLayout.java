package com.lrchao.views.itemview;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lrchao.views.R;
import com.lrchao.views.Utils;

import static com.lrchao.views.Utils.dip2px;


/**
 * Description: 标题，输入域，箭头
 * 不要 setMargins(0, 0, DensityUtils.dip2px(25), 0); 否则会导致EditText的weight失效
 *
 * @author liuranchao
 * @date 16/4/23 下午5:43
 */
public class EditTextItemLayout extends LinearLayout implements TextWatcher {


    /**
     * 标题
     */
    private TextView mTvTitle;

    /**
     * 内容
     */
    private EditText mEtContent;

    /**
     * 输入域后面的单位
     */
    private TextView mTvUnit;

    /**
     * 修改前的字符
     */
    private CharSequence mCharBefore;

    private OnItemTextEditChangedListener mOnItemTextEditChangedListener;

    public EditTextItemLayout(Context context) {
        super(context);
        init(context);
    }

    public EditTextItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void setOnItemTextEditChangedListener(OnItemTextEditChangedListener onItemTextEditChangedListener) {
        mOnItemTextEditChangedListener = onItemTextEditChangedListener;
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        setBackgroundResource(android.R.color.white);
        int paddingValue = dip2px(15);
        setPadding(paddingValue, paddingValue, paddingValue, paddingValue);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater.from(context).inflate(R.layout.itemview_edit_text, this);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mEtContent = (EditText) findViewById(R.id.et_content);
        mEtContent.addTextChangedListener(this);
        mTvUnit = (TextView) findViewById(R.id.tv_unit);

    }

    public String getContentText() {
        if (mEtContent != null && !TextUtils.isEmpty(mEtContent.getText())) {
            return mEtContent.getText().toString().trim();
        }
        return "";
    }

    public void setContentTextMaxLength(int maxLength) {
        if (mEtContent != null) {
            mEtContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        }
    }

    /**
     * 显示软键盘
     */
    public void showSoftKeyboard() {
        Utils.showDelay(mEtContent, getContext());
    }

    /**
     * 设置hint文本
     *
     * @param hintTextResId strings.xml
     */
    public void setHintText(@StringRes int hintTextResId) {
        mEtContent.setHint(hintTextResId);
    }

    /**
     * 设置hint文本
     *
     * @param hintText hint text
     */
    public void setHintText(CharSequence hintText) {
        mEtContent.setHint(hintText);
    }

    /**
     * 设置标题
     *
     * @param strId strings.xml
     */
    public void setTitle(@StringRes int strId) {
        mTvTitle.setText(strId);
    }

    public void setTitle(CharSequence title) {
        if (mTvTitle != null) {
            mTvTitle.setText(title);
        }
    }

    public void setTitleTextSize(int textSize) {
        if (mTvTitle != null) {
            mTvTitle.setTextSize(textSize);
        }
    }

    public void setTitleTextColor(@ColorRes int color) {
        if (mTvTitle != null) {
            mTvTitle.setTextColor(Utils.getColor(color));
        }
    }

    public void setHintTextColor(@ColorRes int color) {
        if (mEtContent != null) {
            mEtContent.setHintTextColor(Utils.getColor(color));
        }
    }

    public void setContentTextSize(int textSize) {
        if (mEtContent != null) {
            mEtContent.setTextSize(textSize);
        }
    }

    public void setContentTextColor(@ColorRes int color) {
        if (mEtContent != null) {
            mEtContent.setTextColor(Utils.getColor(color));
        }
    }

    public void setContentMargin(int left, int top, int right, int bottom) {
        if (mEtContent != null) {
            LayoutParams params = (LayoutParams) mEtContent.getLayoutParams();
            params.setMargins(dip2px(left), dip2px(top), dip2px(right), dip2px(bottom));
            mEtContent.setLayoutParams(params);
        }
    }

    /**
     * 获取内容
     */
    public String getContent() {
        return mEtContent.getText().toString().trim();
    }

    /**
     * 设置文本内容
     *
     * @param content 文本内容
     */
    public void setContent(CharSequence content) {
        mEtContent.setText(content);
        if (!TextUtils.isEmpty(content)) {
            mEtContent.setSelection(content.length());
        }
    }

    /**
     * 设置输入限制
     * InputType.TYPE_NUMBER_FLAG_DECIMAL
     */
    public void setEditTextInputType(int inputType) {
        if (mEtContent != null) {
            mEtContent.setInputType(inputType);
        }
    }

    /**
     * 设置单位
     *
     * @param resId strings.xml
     */
    public void setUnit(@StringRes int resId) {
        mTvUnit.setText(resId);
    }

    /**
     * 设置输入与的对其方式
     *
     * @param gravity Gravity.LEFT etc.
     */
    public void setEditGravity(int gravity) {
        if (mEtContent != null) {
            mEtContent.setGravity(gravity);
        }
    }

    /**
     * 设置输入域的最大字数
     *
     * @param length 最大字数
     */
    public void maxLength(int length) {
        if (mEtContent != null) {
            InputFilter[] filters = {new InputFilter.LengthFilter(length)};
            mEtContent.setFilters(filters);
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        mCharBefore = String.valueOf(s);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mOnItemTextEditChangedListener != null &&
                (!TextUtils.isEmpty(s) || !TextUtils.isEmpty(mCharBefore))) {
            mOnItemTextEditChangedListener.onItemTextEditChanged(String.valueOf(s));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * 监听edit text变化
     */
    public interface OnItemTextEditChangedListener {
        /**
         * @param s 变化后的字符串
         */
        void onItemTextEditChanged(String s);
    }
}
