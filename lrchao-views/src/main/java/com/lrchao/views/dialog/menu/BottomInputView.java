package com.lrchao.views.dialog.menu;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lrchao.utils.ResourceUtils;
import com.lrchao.utils.SoftKeyboardUtils;
import com.lrchao.views.LrchaoViews;
import com.lrchao.views.R;
import com.lrchao.views.gridview.add_pic.AddPicGridView;

import java.util.List;

/**
 * Description: 底部输入的view
 *
 * @author lrc19860926@gmail.com
 * @date 2016/12/5 下午5:06
 */

public class BottomInputView extends LinearLayout implements TextWatcher, View.OnClickListener {

    private TextView mTvTitle;

    private EditText mEtComment;

    private ImageView mIvAddPic;

    private TextView mTvSubmit;

    private AddPicGridView mAddPicGridView;

    /**
     * 是否显示图片
     */
    private boolean mIsShowAddPic = true;

    private OnClickSubmitListener mOnClickSubmitListener;

    public BottomInputView(Context context) {
        super(context);
        init(context);
    }

    public BottomInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BottomInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 设置点击提交监听
     */
    public void setOnClickSubmitListener(OnClickSubmitListener onClickSubmitListener) {
        mOnClickSubmitListener = onClickSubmitListener;
    }

    /**
     * 设置顶部的标题
     *
     * @param title 标题
     */
    public void setTitle(CharSequence title) {
        bindTitleView(title);
    }

    /**
     * 是否要添加图片
     *
     * @param isAddPic true 添加图片
     */
    public void setAddPic(boolean isAddPic) {
        bindAddPicView(isAddPic);
    }

    /**
     * 设置输入域的提示语
     *
     * @param hintText 提示语
     */
    public void setHintText(CharSequence hintText) {
        bindEditTextView(hintText);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_bottom_input, this);
        setOrientation(VERTICAL);
        setMinimumWidth(9999);
        setBackgroundResource(R.color.bottom_input_bg);

        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setVisibility(View.GONE);
        mEtComment = (EditText) findViewById(R.id.et_comment);
        mEtComment.addTextChangedListener(this);
        mIvAddPic = (ImageView) findViewById(R.id.iv_add_pic);
        mTvSubmit = (TextView) findViewById(R.id.tv_submit);

        mAddPicGridView = (AddPicGridView) findViewById(R.id.grid_view_add_pic);
        mAddPicGridView.setItemLayoutId(R.layout.gridview_add_pic_item);
        mAddPicGridView.setColumnNumber(4);
        mAddPicGridView.setAddViewRes(R.drawable.add_pic_icon);
        mAddPicGridView.setMaxCount(9);

        mEtComment.setOnClickListener(this);
        mTvSubmit.setOnClickListener(this);
        mIvAddPic.setOnClickListener(this);

        bindTitleView("");
        bindEditTextView(ResourceUtils.getString(R.string.hint_input_bottom_msg));
        bindAddPicView(true);
        bindSubmitUnClickableView();
        bindNotShowAddPicView();


    }


    private void bindAddPicView(boolean isAddPic) {
        if (isAddPic) {
            mIvAddPic.setVisibility(View.VISIBLE);
        } else {
            mIvAddPic.setVisibility(View.GONE);
        }
    }

    private void bindEditTextView(CharSequence hintText) {
        mEtComment.setHint(hintText);
    }

    private void bindTitleView(CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
            mTvTitle.setVisibility(View.VISIBLE);
        } else {
            mTvTitle.setVisibility(View.GONE);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!TextUtils.isEmpty(s.toString())) {
            bindSubmitClickableView();
        } else {
            bindSubmitUnClickableView();
        }
    }

    private void bindSubmitClickableView() {
        mTvSubmit.setBackgroundResource(R.drawable.bottom_view_submit_clickable);
        mTvSubmit.setClickable(true);
    }

    private void bindSubmitUnClickableView() {
        mTvSubmit.setBackgroundResource(R.drawable.bottom_view_submit_un_clickable);
        mTvSubmit.setClickable(false);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_add_pic) {
            clickAddPic();
        } else if (i == R.id.et_comment) {
            bindNotShowAddPicView();
        } else if (i == R.id.tv_submit) {
            if (mOnClickSubmitListener != null) {
                mOnClickSubmitListener.onClickSubmit(mEtComment.getText().toString(), mAddPicGridView.getSelectedData());
            }
        }
    }

    private void clickAddPic() {
        if (mIsShowAddPic) {
            bindNotShowAddPicView();
        } else {
            bindShowAddPicView();
        }

    }

    private void bindNotShowAddPicView() {
        mIvAddPic.setBackgroundResource(R.drawable.bottom_view_add_pic_not_show);
        mAddPicGridView.setVisibility(View.GONE);
        mIsShowAddPic = false;
    }

    private void bindShowAddPicView() {
        mIvAddPic.setBackgroundResource(R.drawable.bottom_view_add_pic_show);
        mAddPicGridView.setVisibility(View.VISIBLE);
        mIsShowAddPic = true;
        SoftKeyboardUtils.hideKeyboard(LrchaoViews.getInstance().getContext(), mEtComment);
    }


    public interface OnClickSubmitListener {
        void onClickSubmit(String text, List<String> filePathList);
    }
}
