package com.lrchao.views.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.lrchao.utils.ImageUtils;
import com.lrchao.views.R;
import com.lrchao.views.gridview.OnClickAddPicGridAdapterListener;
import com.lrchao.views.model.AddPicModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: GridView添加图片的adapter
 *
 * @author lrc19860926@gmail.com
 * @date 2016/12/6 上午10:31
 */

public class AddPicGridViewAdapter extends BaseNormalAdapter<AddPicModel> {

    /**
     * Item layout id
     */
    private int mItemLayoutId;

    private OnClickAddPicGridAdapterListener mOnClickAddPicGridAdapterListener;

    public AddPicGridViewAdapter(Context context, List dataSource) {
        super(context, dataSource);
    }

    public void setOnClickAddPicGridAdapterListener(OnClickAddPicGridAdapterListener listener) {
        mOnClickAddPicGridAdapterListener = listener;
    }

    public List<String> getFilePathList() {
        List<String> filePathList = new ArrayList<>();
        for (AddPicModel model : getDataSource()) {
            if (!TextUtils.isEmpty(model.getFilePath())) {
                filePathList.add(model.getFilePath());
            }
        }
        return filePathList;
    }

    @Override
    protected int getItemLayoutId() {
        return mItemLayoutId;
    }

    public final void setItemLayoutId(@LayoutRes int layoutId) {
        mItemLayoutId = layoutId;
    }

    @Override
    protected BaseNormalViewHolder createViewHolder() {
        return new AddPicViewHolder();
    }

    @Override
    protected void initViewHolder(View convertView, BaseNormalViewHolder holder) {
        AddPicViewHolder viewHolder = (AddPicViewHolder) holder;
        viewHolder.mIvIcon = (ImageView) convertView.findViewById(R.id.iv_grid_item_icon);
        viewHolder.mIvClose = (ImageView) convertView.findViewById(R.id.iv_grid_item_close);
    }

    @Override
    protected void bindViewHolder(BaseNormalViewHolder holder, final int position) {
        final AddPicModel model = getItem(position);
        AddPicViewHolder viewHolder = (AddPicViewHolder) holder;
        if (model != null) {


            if (model.isNormalView()) {
                ImageUtils.display(new File(model.getFilePath()),
                        viewHolder.mIvIcon,
                        0,
                        null,
                        true,
                        0,
                        0,
                        false,
                        false);

            } else if (model.isAddView()) {
                ImageUtils.display(model.getAddViewResId(),
                        viewHolder.mIvIcon,
                        0,
                        null,
                        true,
                        0,
                        0,
                        false,
                        false);
            }

            viewHolder.mIvIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mOnClickAddPicGridAdapterListener != null) {
                        if (model.isAddView()) {
                            mOnClickAddPicGridAdapterListener.onClickAddPick();
                        } else {
                            mOnClickAddPicGridAdapterListener.onClickPic(model.getFilePath());
                        }
                    }

                }
            });


            if (model.isShowClose()) {
                viewHolder.mIvClose.setVisibility(View.VISIBLE);
            } else {
                viewHolder.mIvClose.setVisibility(View.GONE);
            }

            viewHolder.mIvClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnClickAddPicGridAdapterListener != null) {
                        mOnClickAddPicGridAdapterListener.onClickClose(position);
                    }
                }
            });

        }
    }

    static class AddPicViewHolder implements BaseNormalViewHolder {
        ImageView mIvIcon;
        ImageView mIvClose;
    }
}
