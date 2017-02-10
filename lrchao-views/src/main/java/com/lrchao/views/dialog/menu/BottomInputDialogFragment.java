package com.lrchao.views.dialog.menu;

import android.view.View;

import com.lrchao.views.R;

/**
 * Description: 输入的dialog
 * 1.
 *
 * @author lrc19860926@gmail.com
 * @date 2016/12/5 下午5:00
 */

public class BottomInputDialogFragment extends BaseMenuDialogFragment {


    private BottomInputView mBottomInputView;

    private BottomInputView.OnClickSubmitListener mOnClickSubmitListener;

    private CharSequence mTitle;

    private CharSequence mHintText;

    private boolean mIsAddPic;

    public static BottomInputDialogFragment getInstance() {
        return new BottomInputDialogFragment();
    }

    public void setOnClickSubmitListener(BottomInputView.OnClickSubmitListener onClickSubmitListener) {
        mOnClickSubmitListener = onClickSubmitListener;
    }

    public void setAddPic(boolean addPic) {
        mIsAddPic = addPic;
    }

    public void setTitle(CharSequence title) {
        mTitle = title;
    }

    public void setHintText(CharSequence hintText) {
        mHintText = hintText;
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.dialog_bottom_input;
    }

    @Override
    protected void initView(View parentView) {
        mBottomInputView = (BottomInputView) parentView.findViewById(R.id.bottom_input_view);
        mBottomInputView.setOnClickSubmitListener(mOnClickSubmitListener);
        mBottomInputView.setTitle(mTitle);
        mBottomInputView.setHintText(mHintText);
        mBottomInputView.setAddPic(mIsAddPic);
    }

}
