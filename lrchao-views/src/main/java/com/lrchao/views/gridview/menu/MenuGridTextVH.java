package com.lrchao.views.gridview.menu;

import android.view.View;
import android.widget.TextView;

import com.lrchao.views.R;
import com.lrchao.views.gridview.LrchaoGridVH;


/**
 * Description: MenuGrid带文字标题和文字内容的VH
 *
 * @author lrc19860926@gmail.com
 * @date 2016/10/20 下午3:32
 */

public class MenuGridTextVH extends LrchaoGridVH {

    public TextView mTvTitle;

    public TextView mTvContent;

    public MenuGridTextVH(View itemView) {
        super(itemView);
        mTvTitle = (TextView) itemView.findViewById(R.id.tv_menu_title);
        mTvContent = (TextView) itemView.findViewById(R.id.tv_menu_content);
    }
}
