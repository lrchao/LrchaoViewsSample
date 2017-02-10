package com.lrchao.views.gridview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.lrchao.views.R;


/**
 * Description: MenuGrid布局
 *
 * @author lrc19860926@gmail.com
 * @date 2016/10/20 下午1:44
 */

public class LrchaoGridLayout extends LinearLayout {

    private static final int DEFAULT_SPAN_COUNT = 3;

    private RecyclerView mRecyclerView;

    private GridLayoutManager mLayoutManager;

    public LrchaoGridLayout(Context context) {
        super(context);
        init(context);
    }

    public LrchaoGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    //============================================================
    // private
    //============================================================

    /**
     * 设置adapter
     *
     * @param adapter LrchaoGridAdapter
     */
    public void setAdapter(LrchaoGridAdapter adapter) {
        if (mRecyclerView != null && adapter != null) {
            adapter.setColumnCount(mLayoutManager.getSpanCount());
            mRecyclerView.setAdapter(adapter);
            adapter.setRecyclerView(mRecyclerView);
        }
    }

    /**
     * 设置几列
     *
     * @param count 列数
     */
    public void setSpanCount(int count) {
        if (mLayoutManager != null) {
            mLayoutManager.setSpanCount(count);
        }
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.gridview_layout, this);
        setOrientation(VERTICAL);
        setBackgroundResource(android.R.color.white);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(context, DEFAULT_SPAN_COUNT);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


}
