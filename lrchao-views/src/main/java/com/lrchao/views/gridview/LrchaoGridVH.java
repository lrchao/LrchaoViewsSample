package com.lrchao.views.gridview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lrchao.views.R;


/**
 * Description: Grid ViewHolder基类
 *
 * @author lrc19860926@gmail.com
 * @date 2016/10/20 下午4:18
 */

public abstract class LrchaoGridVH extends RecyclerView.ViewHolder {

    /**
     * item 底部的线
     */
    public View mViewBottomLine;

    /**
     * item 右部的线
     */
    public View mViewRightLine;

    public LrchaoGridVH(View itemView) {
        super(itemView);
        mViewBottomLine = itemView.findViewById(R.id.v_menu_bottom_line);
        mViewRightLine = itemView.findViewById(R.id.v_menu_right_line);
    }
}
