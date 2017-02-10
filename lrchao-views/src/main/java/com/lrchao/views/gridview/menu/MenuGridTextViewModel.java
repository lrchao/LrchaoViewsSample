package com.lrchao.views.gridview.menu;


import com.lrchao.views.gridview.LrchaoGridViewModel;

/**
 * Description: 带文字标题和文字内容
 *
 * @author lrc19860926@gmail.com
 * @date 2016/10/20 下午3:27
 */

public interface MenuGridTextViewModel extends LrchaoGridViewModel {

    /**
     * 标题
     */
    CharSequence getTitle();

    /**
     * 内容
     */
    CharSequence getContent();
}
