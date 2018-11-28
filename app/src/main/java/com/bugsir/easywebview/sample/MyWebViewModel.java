package com.bugsir.easywebview.sample;
import android.view.View;
import android.webkit.WebView;

import com.bugsir.easywebview.webview.BaseWebViewModel;

/**
 *@author: BUG SIR
 *@date: 2018/11/13 16:48
 *@description: 
 */
public    class MyWebViewModel extends BaseWebViewModel   {
    @Override
    public View getLayout() {
        WebView webView=new WebView(getModelCallback().getModelActivity());
        this.mWebView=webView;
        return webView;
    }

}
