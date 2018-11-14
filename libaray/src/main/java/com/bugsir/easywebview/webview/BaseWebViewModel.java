package com.bugsir.easywebview.webview;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.bugsir.easywebview.base.BaseModel;

/**
 *@author: BUG SIR
 *@date: 2018/11/13 14:29
 *@description: 自带webview的使用
 */
public  abstract   class BaseWebViewModel extends BaseModel<WebView> {

    @Override
    public void loadUrl(String url) {
        if (mWebView==null)
            return;
        if (url.startsWith("http://") || url.startsWith("https://")) {
            mWebView.loadUrl(url);
        } else if(url.startsWith("file:"))
        {
            mWebView.loadUrl(url);
        }else if(url.contains("html")||url.contains("http"))
        {
            mWebView.loadDataWithBaseURL(null, url,"text/html" , "utf-8", null);
        } {
            mWebView.loadUrl("http://" + url);
        }
    }

    @Override
    public void setWebViewSetting() {
        if (mWebView==null)
        {
            return;
        }
        WebSettings webSetting=mWebView.getSettings();
        // 缓存
        if (isSetWebCache())
        {
            webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            webSetting.setAppCacheEnabled(true);
        } else
        {
            webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
            webSetting.setAppCacheEnabled(false);
        }
        webSetting.setSupportZoom(true);//是否可以缩放，默认true
        webSetting.setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        webSetting.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webSetting.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webSetting.setDomStorageEnabled(true);//DOM Storage
        webSetting.setDisplayZoomControls(false);
        webSetting.setDatabaseEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setDefaultTextEncodingName("UTF-8");
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        setWebClient();
        setWebChromeClient();
    }

    /**
     * 设置webClient,此方法默认在setWebViewSetting 会调用，若重写setWebViewSetting 没有supper 请手动调用一下此方法
     */
    public void setWebClient()
    {
        if (mWebView==null)
            return;
        mWebView.setWebViewClient(new BaseWebViewClient());
    }
    /**
     * 设置WebChromeClient,此方法默认在setWebViewSetting 会调用，若重写setWebViewSetting 没有supper 请手动调用一下此方法
     */
    public void setWebChromeClient()
    {
        if (mWebView==null)
            return;
    }

    @SuppressLint("JavascriptInterface")
    @Override
    public void addJavascriptInterface() {
        if (mWebView==null)
        {
            return;
        }
        if (!TextUtils.isEmpty(getJsName()))
        {
            mWebView.addJavascriptInterface(this,getJsName());
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        }
    }

    @Override
    public String getJsName() {
        return null;
    }
    /**
     * 处理Activity的返回事件
     *
     * @return true为关闭页面
     */
    @Override
    public boolean clickBack() {
        if (mWebView != null && mWebView.canGoBack())
        {
            mWebView.goBack();
            return false;
        }
        return true;
    }

    @Override
    public void destroy() {
        if (mWebView != null)// 清除
        {
            mWebView.stopLoading();
            // 先让 WebView 加载null内容，然后移除 WebView，再销毁 WebView，最后置空。
            mWebView.setWebChromeClient(null);
            mWebView.setWebViewClient(null);
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearCache(true);
            mWebView.clearHistory();
            mWebView.clearFormData();
            mWebView.clearSslPreferences();
            mWebView.clearMatches();
            mWebView.removeAllViews();
        }
    }
}
