package com.lrchao.lrchaoviewssample.popupwindow;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lrchao.lrchaoviewssample.R;
import com.lrchao.utils.LogUtils;
import com.lrchao.views.gridview.LrchaoGridAdapter;
import com.lrchao.views.gridview.LrchaoGridViewModel;
import com.lrchao.views.gridview.menu.MenuGridIconTextAdapter;

import java.util.ArrayList;
import java.util.List;

public class PopupWindowActivity extends AppCompatActivity  implements LrchaoGridAdapter.OnLrchaoItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);

        findViewById(R.id.btn_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MenuGridIconTextAdapter menuAdapter = new MenuGridIconTextAdapter(null);
                menuAdapter.setLayoutResId(R.layout.constr_gridview_item);
                menuAdapter.setOnLrchaoItemClickListener(PopupWindowActivity.this);

                List<LrchaoGridViewModel> data = new ArrayList<>();
                data.add(bindGridView(R.drawable.popup_window_constr_info, "工地信息"));//0
                data.add(bindGridView(R.drawable.popup_window_constr_info, "过程记录"));//1
                data.add(bindGridView(R.drawable.popup_window_constr_info, "施工排期"));//2
                data.add(bindGridView(R.drawable.popup_window_constr_info, "照片审核"));//3
                data.add(bindGridView(R.drawable.popup_window_constr_info, "整改记录"));//4
                data.add(bindGridView(R.drawable.popup_window_constr_info, "合同款项"));//5
                data.add(bindGridView(R.drawable.popup_window_constr_info, "开单收款"));//6
                data.add(bindGridView(R.drawable.popup_window_constr_info, "施工单据"));//7
                data.add(bindGridView(R.drawable.popup_window_constr_info, "签到记录"));//8
                menuAdapter.setDataSource(data);

                MenuPopupWindow mPopupWindowMenu = new MenuPopupWindow(PopupWindowActivity.this);

                mPopupWindowMenu.setAdapter(menuAdapter);

                mPopupWindowMenu.showAsDropDown(v, 0, 0);
            }
        });
    }

    private ConstrPopupWindowViewModel bindGridView(@DrawableRes int icon, CharSequence text) {
        ConstrPopupWindowViewModel model = new ConstrPopupWindowViewModel();
        model.setIcon(icon);
        model.setTitle(text);
        return model;
    }

    @Override
    public void onItemClick(LrchaoGridViewModel item, int itemPosition) {
        LogUtils.e("itemPosition==" + itemPosition);
    }
}
