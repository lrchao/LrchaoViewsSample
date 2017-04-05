package com.lrchao.lrchaoviewssample.popupwindow;

import android.support.annotation.DrawableRes;

import com.lrchao.views.gridview.menu.MenuGridIconTextViewModel;


/**
 * Description:
 *
 * @author lrc19860926@gmail.com
 * @date 2016/11/25 上午11:24
 */

public class ConstrPopupWindowViewModel implements MenuGridIconTextViewModel {

    private CharSequence mTitle;

    @DrawableRes
    private int mIcon;

    public void setTitle(CharSequence title) {
        mTitle = title;
    }

    public void setIcon(@DrawableRes int icon) {
        mIcon = icon;
    }

    @Override
    public CharSequence getTitle() {
        return mTitle;
    }

    @Override
    public int getIcon() {
        return mIcon;
    }
}
