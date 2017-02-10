package com.lrchao.views.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 一般的adapter,不是基于recycler view
 *
 * @author lrc19860926@gmail.com
 * @date 2016/12/6 上午10:43
 */

public abstract class BaseNormalAdapter<T> extends BaseAdapter {

    private List<T> mDataSource;

    private LayoutInflater mInflater;

    public BaseNormalAdapter(Context context, List<T> dataSource) {
        mInflater = LayoutInflater.from(context);
        if (dataSource == null) {
            mDataSource = new ArrayList<>();
        } else {
            mDataSource = dataSource;
        }
    }

    //=============================================
    // 子类继承
    //=============================================

    /**
     * 设置item layout id
     */
    protected abstract
    @LayoutRes
    int getItemLayoutId();

    /**
     * 创建个view holder
     */
    protected abstract BaseNormalViewHolder createViewHolder();

    /**
     * 初始化view holder
     */
    protected abstract void initViewHolder(View convertView, BaseNormalViewHolder holder);

    /**
     * 绑定view
     */
    protected abstract void bindViewHolder(BaseNormalViewHolder holder, int position);

    //=============================================
    //  公共的
    //=============================================


    public List<T> getDataSource() {
        return mDataSource;
    }

    /**
     * 添加数据
     */
    public void addAll(List<T> data) {
        addAll(0, data);
    }

    public void addAll(int index, List<T> data) {
        if (data != null && data.size() > 0 && getCount() >= index) {
            mDataSource.addAll(index, data);
            notifyAdapter();
        }
    }

    public void add(T item) {
        add(0, item);
    }

    /**
     * 插入单条数据
     *
     * @param item     单条数据
     * @param position 位置
     */
    public void add(int position, T item) {
        if (item != null && getCount() >= position) {
            mDataSource.add(position, item);
            notifyAdapter();
        }
    }

    /**
     * 移除某个位置的数据
     *
     * @param position 索引
     */
    public void remove(int position) {
        if (position < getCount()) {
            mDataSource.remove(position);
            notifyAdapter();
        }
    }

    /**
     * 清空数据
     */
    public void clear() {
        mDataSource.clear();
        notifyAdapter();
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public T getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseNormalViewHolder holder;

        if (convertView == null && getItemLayoutId() > 0) {
            convertView = mInflater.inflate(getItemLayoutId(),
                    null);
            holder = createViewHolder();
            initViewHolder(convertView, holder);
            convertView.setTag(holder);

        } else {
            holder = (BaseNormalViewHolder) convertView.getTag();
        }

        bindViewHolder(holder, position);
        return convertView;
    }


    private void notifyAdapter() {
        notifyDataSetChanged();
    }
}
