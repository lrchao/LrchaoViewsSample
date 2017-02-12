package com.lrchao.views.swipe;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lrchao.views.R;
import com.lrchao.views.scrollview.MyScrollView;
import com.lrchao.views.webview.MyWebView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 自定义的SwipeRefreshLayout
 * 防止scroll view滑动冲突
 *
 * @author liuranchao
 * @date 16/3/8 下午8:33
 */
public class CustomSwipeRefreshLayout extends SwipeRefreshLayout {

    /**
     * 子view中的scroll view
     */
    private MyScrollView mScrollView;

    /**
     * 子view中的WebView
     */
    private MyWebView mCustomWebView;

    private ListView mListView;

    public CustomSwipeRefreshLayout(Context context) {
        super(context);
    }

    public CustomSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 初始化view
     */
    public void init() {
        setColorSchemeResources(R.color.swipe_refresh_red,
                R.color.swipe_refresh_blue,
                R.color.swipe_refresh_yellow,
                R.color.swipe_refresh_green);

        List<View> views = getAllChildViews(this);
        for (View v : views) {
            if (v instanceof MyScrollView) {
                mScrollView = (MyScrollView) v;
            } else if (v instanceof MyWebView) {
                mCustomWebView = (MyWebView) v;
            } else if (v instanceof ListView) {
                mListView = (ListView) v;
            }
        }
    }

    @Override
    public boolean canChildScrollUp() {
        if (mScrollView != null) {
            return mScrollView.canScrollVertically(-1);
        }

        if (mCustomWebView != null) {
            return mCustomWebView.canScrollVertically(-1);
        }

        if (mListView != null) {
            return mListView.getFirstVisiblePosition() > 0;
        }
        return false;
    }

    /**
     * 获取所有子view
     *
     * @param view 要遍历的view
     */
    private List<View> getAllChildViews(View view) {

        List<View> views = new ArrayList<>();

        if (view instanceof ViewGroup) {

            ViewGroup vp = (ViewGroup) view;

            for (int i = 0; i < vp.getChildCount(); i++) {

                View viewChild = vp.getChildAt(i);

                views.add(viewChild);

                views.addAll(getAllChildViews(viewChild));
            }
        }
        return views;

    }
}
