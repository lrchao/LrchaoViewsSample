package com.lrchao.views.gridview;

import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lrchao.views.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: GridAdapter的基类
 *
 * @author lrc19860926@gmail.com
 * @date 2016/10/20 下午3:51
 */

public abstract class LrchaoGridAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    /**
     * 数据源
     */
    protected List<LrchaoGridViewModel> mDataSource;
    /**
     * RecyclerView
     */
    protected RecyclerView mRecyclerView;
    /**
     * item layout
     */
    private int mLayoutResId;
    /**
     * Item click listener
     */
    private OnLrchaoItemClickListener mOnLrchaoItemClickListener;

    /**
     * 列数
     */
    private int mColumnCount;

    public LrchaoGridAdapter(List<LrchaoGridViewModel> data) {

        if (data == null) {
            mDataSource = new ArrayList<>();
        } else {
            mDataSource = data;
        }
    }

    public void setColumnCount(int columnCount) {
        mColumnCount = columnCount;
    }

    /**
     * 设置item点击
     *
     * @param onLrchaoItemClickListener OnLrchaoItemClickListener
     */
    public void setOnLrchaoItemClickListener(OnLrchaoItemClickListener onLrchaoItemClickListener) {
        mOnLrchaoItemClickListener = onLrchaoItemClickListener;
    }

    /**
     * 设置RecyclerView
     *
     * @param recyclerView RecyclerView
     */
    public void setRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    /**
     * 设置数据源
     *
     * @param dataSource List<LrchaoGridViewModel>
     */
    public void setDataSource(List<LrchaoGridViewModel> dataSource) {
        mDataSource = dataSource;
        notifyDataSetChanged();
    }

    /**
     * 设置item layout
     *
     * @param layoutResId 布局文件ID
     */
    public void setLayoutResId(@LayoutRes int layoutResId) {
        mLayoutResId = layoutResId;
    }

    //==================================================
    // private
    //==================================================

    /**
     * 子类创建自己的ViewHolder
     *
     * @param v View
     * @return LrchaoGridVH
     */
    protected abstract LrchaoGridVH createVH(View v);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(mLayoutResId, parent, false);
        v.setOnClickListener(this);
        return createVH(v);
    }

    @CallSuper
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LrchaoGridViewModel model = mDataSource.get(position);
        LrchaoGridVH vh = (LrchaoGridVH) holder;
        if (model != null) {

            // 针对 最后一行不显示
            if (vh.mViewBottomLine != null) {
                if (isBottomRow(mDataSource.size(), mColumnCount, position)) {
                    vh.mViewBottomLine.setVisibility(View.GONE);
                } else {
                    vh.mViewBottomLine.setVisibility(View.VISIBLE);
                }
            }

            // 针对 是不是每一行的最后一个
            if (vh.mViewRightLine != null) {
                if (isRowLast(position, mColumnCount)) {
                    vh.mViewRightLine.setVisibility(View.GONE);
                } else {
                    vh.mViewRightLine.setVisibility(View.VISIBLE);
                }
            }

        }
    }

    private boolean isRowLast(int position, int columnCount) {
        return (position + 1) % columnCount == 0;
    }

    private boolean isBottomRow(int total, int columnCount, int position) {
        int totalRow = Utils.getTotalRowNum(total, columnCount);
        int currentRow = Utils.getCurrentRowNum(position, columnCount);
        return currentRow == totalRow;
    }

    @Override
    public int getItemCount() {
        if (mDataSource == null) {
            return 0;
        }
        return mDataSource.size();
    }

    @Override
    public void onClick(View v) {
        int itemPosition = mRecyclerView.getChildLayoutPosition(v);
        LrchaoGridViewModel item = mDataSource.get(itemPosition);
        if (mOnLrchaoItemClickListener != null) {
            mOnLrchaoItemClickListener.onItemClick(item, itemPosition);
        }
    }

    public interface OnLrchaoItemClickListener {

        void onItemClick(LrchaoGridViewModel item, int itemPosition);
    }
}
