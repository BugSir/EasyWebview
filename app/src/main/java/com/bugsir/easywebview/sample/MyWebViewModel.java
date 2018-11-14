package com.bugsir.easywebview.sample;
import android.app.Activity;
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
    public View getLayout(Activity activity) {
        WebView webView=new WebView(activity);
        this.mWebView=webView;
        return webView;
    }

    @Override
    public void setTitle(String title) {

    }
}
