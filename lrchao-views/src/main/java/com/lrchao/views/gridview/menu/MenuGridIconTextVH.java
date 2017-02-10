package com.lrchao.views.gridview.menu;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lrchao.views.R;
import com.lrchao.views.gridview.LrchaoGridVH;

/**
 * Description: MenuGrid带图标和文字标题的VH
 *
 * @author lrc19860926@gmail.com
 * @date 2016/11/25 上午11:12
 */

public class MenuGridIconTextVH extends LrchaoGridVH {

    public TextView mTvTitle;

    public ImageView mIvIcon;

    public MenuGridIconTextVH(View itemView) {
        super(itemView);

        mTvTitle = (TextView) itemView.findViewById(R.id.tv_menu_title);
        mIvIcon = (ImageView) itemView.findViewById(R.id.iv_menu_icon);
    }
}
