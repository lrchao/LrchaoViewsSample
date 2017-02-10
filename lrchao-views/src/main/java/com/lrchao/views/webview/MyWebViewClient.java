package com.lrchao.views.webview;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Description: 自定义的WebViewClient
 *
 * @author lrc19860926@gmail.com
 * @date 16/3/4 上午10:34
 */
public class MyWebViewClient extends WebViewClient {

    // 在 webView中打开地址
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }
}
