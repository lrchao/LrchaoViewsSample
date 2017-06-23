package com.lrchao.views.gridview.menu;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lrchao.views.gridview.LrchaoGridAdapter;
import com.lrchao.views.gridview.LrchaoGridVH;
import com.lrchao.views.gridview.LrchaoGridViewModel;

import java.util.List;


/**
 * Description: 上面图标，下面文字内容的adapter
 *
 * @author lrc19860926@gmail.com
 * @date 2016/11/25 上午11:11
 */

public class MenuGridIconTextAdapter extends LrchaoGridAdapter {

    public MenuGridIconTextAdapter(List<LrchaoGridViewModel> data) {
        super(data);
    }

    @Override
    protected LrchaoGridVH createVH(View v) {
        return new MenuGridIconTextVH(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        MenuGridIconTextViewModel model = (MenuGridIconTextViewModel) mDataSource.get(position);

        MenuGridIconTextVH vh = (MenuGridIconTextVH) holder;
        if (model != null) {

            if (vh.mTvTitle != null) {
                vh.mTvTitle.setText(model.getTitle());
            }

            if (vh.mIvIcon != null) {
                vh.mIvIcon.setBackgroundResource(model.getIcon());
            }

        }
    }


}
