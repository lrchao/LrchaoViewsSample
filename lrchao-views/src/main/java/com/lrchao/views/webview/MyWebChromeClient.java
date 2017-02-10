package com.lrchao.views.webview;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Description: 自定义的WebChromeClient
 *
 * @author lrc19860926@gmail.com
 * @date 16/3/4 上午10:05
 */
public class MyWebChromeClient extends WebChromeClient {

    /**
     * 加载进度监听
     */
    private OnProgressChangedListener mOnProgressChangedListener;

    public void setOnProgressChangedListener(OnProgressChangedListener onProgressChangedListener) {
        mOnProgressChangedListener = onProgressChangedListener;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (mOnProgressChangedListener != null) {
            mOnProgressChangedListener.onProgressChanged(newProgress);
        }
    }

    public interface OnProgressChangedListener {
        void onProgressChanged(int progress);
    }
}
