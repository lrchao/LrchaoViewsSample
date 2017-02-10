package com.lrchao.views.gridview.menu;

import android.support.annotation.DrawableRes;

import com.lrchao.views.gridview.LrchaoGridViewModel;


/**
 * Description: 带文字标题和图标
 *
 * @author lrc19860926@gmail.com
 * @date 2016/11/25 上午11:20
 */

public interface MenuGridIconTextViewModel extends LrchaoGridViewModel {

    /**
     * 标题
     */
    CharSequence getTitle();

    /**
     * 图标
     */
    @DrawableRes
    int getIcon();
}
