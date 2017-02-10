package com.lrchao.views.gridview.menu;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lrchao.views.gridview.LrchaoGridAdapter;
import com.lrchao.views.gridview.LrchaoGridVH;
import com.lrchao.views.gridview.LrchaoGridViewModel;

import java.util.List;


/**
 * Description: 上面标题，下面文字内容的adapter
 *
 * @author lrc19860926@gmail.com
 * @date 2016/10/20 下午3:23
 */

public class MenuGridTextAdapter extends LrchaoGridAdapter {

    public MenuGridTextAdapter(List<LrchaoGridViewModel> data) {
        super(data);
    }

    @Override
    protected LrchaoGridVH createVH(View v) {
        return new MenuGridTextVH(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        MenuGridTextViewModel model = (MenuGridTextViewModel) mDataSource.get(position);

        MenuGridTextVH vh = (MenuGridTextVH) holder;
        if (model != null) {
            vh.mTvTitle.setText(model.getTitle());
            vh.mTvContent.setText(model.getContent());
        }
    }

}
