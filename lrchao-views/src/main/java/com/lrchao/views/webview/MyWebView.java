package com.lrchao.views.webview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Description: 封装WebView
 *
 * @author lrc19860926@gmail.com
 * @date 2016/10/18 下午4:53
 */

public class MyWebView extends WebView implements MyWebChromeClient.OnProgressChangedListener {

    /**
     * MIME TYPE
     */
    private static final String MIME_TYPE = "text/html";

    /**
     * ENCODING
     */
    private static final String ENCODING = "UTF-8";

    /**
     * JS调用的协议头
     */
    private static final String JAVASCRIPT = "javascript: ";

    /**
     * 获取到title的监听
     */
    private OnWebViewTitleListener mOnWebViewTitleListener;

    /**
     * 加载进度的监听
     */
    private OnWebProgressChangedListener mOnWebProgressChangedListener;

    /**
     * URL
     */
    private String mUrl;

    /**
     * WebView data
     */
    private String mData;

    /**
     * 返回健，是否执行页面goBack
     * true : 如果页面canGoBack 则 执行goBack() false : 返回给上级
     */
    private boolean mIsGoBackPage;

    //======================================
    // 外部调用
    //======================================

    public MyWebView(Context context) {
        super(context);
        init();
    }


    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 调用Java Script
     *
     * @param jsMethod JS method
     */
    public void invokeJS(String jsMethod) {
        loadUrl(JAVASCRIPT + jsMethod);
    }

    /**
     * 设置是否执行GoBack
     *
     * @param goBackPage 是否执行goBack
     */
    public void setGoBackPage(boolean goBackPage) {
        mIsGoBackPage = goBackPage;
    }

    /**
     * 设置加载链接
     *
     * @param url Url
     */
    public void setUrl(String url) {
        mUrl = url;
    }

    /**
     * 设置加载是数据
     *
     * @param data 网页数据
     */
    public void setData(String data) {
        mData = data;
    }

    /**
     * 加载web view
     */
    public void load() {
        if (!TextUtils.isEmpty(mUrl)) {
            loadUrl(mUrl);
        } else if (!TextUtils.isEmpty(mData)) {
            loadDataWithBaseURL(null, mData, MIME_TYPE, ENCODING, null);
        }
    }

    /**
     * 重新加载当前页面
     */
    public void reloadCurrentPage() {
        reload();
    }

    //======================================
    // 内部使用
    //======================================

    @SuppressLint("JavascriptInterface")
    public void addJSInterface(Object object, String name) {
        addJavascriptInterface(object, name);
    }

    /**
     * 设置标题加载回调
     *
     * @param onWebViewTitleListener OnWebViewTitleListener
     */
    public void setOnWebViewTitleListener(OnWebViewTitleListener onWebViewTitleListener) {
        mOnWebViewTitleListener = onWebViewTitleListener;
    }

    /**
     * 设置加载进度回调
     *
     * @param onWebProgressChangedListener OnWebProgressChangedListener
     */
    public void setOnWebProgressChangedListener(OnWebProgressChangedListener onWebProgressChangedListener) {
        mOnWebProgressChangedListener = onWebProgressChangedListener;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void init() {
        // 设置垂直scroll bar
        setVerticalScrollBarEnabled(true);

        //设置Settings
        WebSettings webSettings = getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        }
        MyWebChromeClient webChromeClient = new MyWebChromeClient();
        webChromeClient.setOnProgressChangedListener(this);

        MyWebViewClient webViewClient = new MyWebViewClient();
        setWebChromeClient(webChromeClient);
        setWebViewClient(webViewClient);
    }

    @Override
    public final void onProgressChanged(int progress) {
        if (mOnWebViewTitleListener != null) {
            mOnWebViewTitleListener.onWebViewTitle(getTitle());
        }

        if (mOnWebProgressChangedListener != null) {
            mOnWebProgressChangedListener.onWebProgressChanged(progress);
        }
    }

    @Override
    public final boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && canGoBack() && mIsGoBackPage) {
            goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
