package com.lrchao.lrchaoviewssample.popupwindow;

import android.content.Context;
import android.view.View;

import com.lrchao.lrchaoviewssample.R;
import com.lrchao.views.gridview.LrchaoGridAdapter;
import com.lrchao.views.gridview.LrchaoGridLayout;
import com.lrchao.views.popupwindow.BasePopupWindow;

/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2016/11/25 上午10:54
 */

public final class MenuPopupWindow extends BasePopupWindow {

    LrchaoGridLayout mGridViewMenu;

    public MenuPopupWindow(Context context) {
        super(context);

//        View view = LayoutInflater.from(context).inflate(R.layout.popup_window_menu, null);
//
//        view.findViewById(R.id.menu_pop_layout).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MenuPopupWindow.this.dismiss();
//            }
//        });
//
//        mGridViewMenu = (LrchaoGridLayout) view.findViewById(R.id.gridivew_menu);
//        setContentView(view);
//        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
//        setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.half_transparent)));
//        setOutsideTouchable(true);
//        setFocusable(true);
    }

    @Override
    protected void initView(View view) {
        mGridViewMenu = (LrchaoGridLayout) view.findViewById(R.id.gridivew_menu);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popup_window_menu;
    }

    public void setAdapter(LrchaoGridAdapter menuAdapter) {
        mGridViewMenu.setAdapter(menuAdapter);
    }
}
