package com.bugsir.easywebview.webview;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 *@author: BUG SIR
 *@date: 2018/11/13 15:12
 *@description: 部分特殊请求地址处理
 */
public    class BaseWebViewClient extends WebViewClient   {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
        if (url == null)
            return false;
        try {
            if (url.startsWith("weixin://") || url.startsWith("alipays://") || url.startsWith("mailto://") || url.startsWith("tel://")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                view.getContext().startActivity(intent);
                return true;
            }
        } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
            return false;
        } //处理http和https开头的url
        view.loadUrl(url);
        return true;
    }
}
