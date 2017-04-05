package com.lrchao.views.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.lrchao.views.R;


/**
 * Description: 基类
 *
 * @author lrc19860926@gmail.com
 * @date 2017/4/5 下午2:49
 */

public abstract class BasePopupWindow extends PopupWindow {

    protected Context mContext;

    public BasePopupWindow(Context context) {
        super(context);
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.popup_window_base, null);

        ViewGroup parentView = (ViewGroup) view.findViewById(R.id.pop_parent);

        parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasePopupWindow.this.dismiss();
            }
        });


        parentView.addView(LayoutInflater.from(context).inflate(getLayoutResId(), null));

        initView(view);
        setContentView(view);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.half_transparent)));
        setOutsideTouchable(true);
        setFocusable(true);
    }

    protected abstract void initView(View view);

    protected abstract int getLayoutResId();

}
