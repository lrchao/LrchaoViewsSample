package com.lrchao.views.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Description: 通用的ScrollView
 * 1. 默认是填满高度
 *
 * @author lrc19860926@gmail.com
 * @date 2016/12/5 下午2:26
 */

public class MyScrollView extends ScrollView {

    private OnScrollViewListener mOnScrollViewListener;

    public MyScrollView(Context context) {
        super(context);
        init();
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setFillViewport(true);
    }

    public void setOnScrollViewListener(OnScrollViewListener onScrollViewListener) {
        this.mOnScrollViewListener = onScrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (mOnScrollViewListener != null) {
            mOnScrollViewListener.onScrollChanged(x, y, oldx, oldy);
        }
    }

    public interface OnScrollViewListener {
        void onScrollChanged(int x, int y, int oldx, int oldy);

    }
}
