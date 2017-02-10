package com.lrchao.views.itemview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.lrchao.views.dividerview.DividerLineView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.lrchao.utils.DensityUtils.dip2px;


/**
 * Description: 垂直的ItemLayout的group
 * 用来控制 是否有 间隔线 显示
 *
 * @author lrc19860926@gmail.com
 * @date 16/7/20 上午11:38
 */
public class ItemGroupLayout extends LinearLayout {

    private Map<Integer, View> mViewMap = new LinkedHashMap<>();

    private List<View> mViewList = new ArrayList<>();

    /**
     * 底部线的左边边距
     */
    private int mBottomLineLeftMargin = 62;

    /**
     * 底部线的左边边距
     */
    private int mBottomLineRightMargin = 0;

    /**
     * 底部线的高度
     */
    private int mBottomLineHeight;

    public ItemGroupLayout(Context context) {
        super(context);
        init(context);
    }

    public ItemGroupLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 设置line 边距
     *
     * @param margin dp
     */
    public void setBottomLineLeftMargin(int margin) {
        mBottomLineLeftMargin = margin;
    }

    /**
     * 设置line 边距
     *
     * @param margin dp
     */
    public void setBottomLineRightMargin(int margin) {
        mBottomLineRightMargin = margin;
    }

    /**
     * 设置底部线的高度
     *
     * @param height px
     */
    public void setBottomLineHeight(int height) {
        mBottomLineHeight = height;
    }

    /**
     * 清除view
     */
    public void clear() {
        removeAllViews();
        mViewList.clear();
    }

    /**
     * add item view
     *
     * @param childView View
     */
    public void addChildView(View childView) {
        mViewList.add(childView);
    }

    //=================================================
    // private
    //=================================================

    /**
     * @param key
     * @param childView
     */
    public void addChildView(int key, View childView) {
        mViewMap.put(key, childView);
    }

    /**
     * 生成整体view
     */
    public void build() {
        for (View view : mViewList) {
            int index = mViewList.indexOf(view);
            addView(view);
            // 只有中间的加分割线
            if (index < mViewList.size() - 1) {
                addView(createDividerView());
            }
        }
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        setBackgroundResource(android.R.color.white);

    }

    private View createDividerView() {
        DividerLineView viewDivider = new DividerLineView(getContext());
        viewDivider.setMarginLeft(dip2px(mBottomLineLeftMargin));
        viewDivider.setMarginRight(dip2px(mBottomLineRightMargin));
        viewDivider.setHeight(mBottomLineHeight);

        return viewDivider;
    }


}
