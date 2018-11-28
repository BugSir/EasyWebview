package com.bugsir.easywebview.base;

import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;

/**
 * @author: BUG SIR
 * @date: 2018/11/13 11:27
 * @description: 公共接口，用于带数据等，方便拓展
 */
public interface IBaseModelImpl {
    /** model 类名 */
    String INTENT_MODEL_CLASS_NAME = "intent_model_class_name";
    /** 初始url */
    String INTENT_WEBVIEW_URL = "intent_webview_url";
    /**activity使用的参数bundle*/
    String INTENT_WEBVIEW_BUNDLE = "intent_webview_bundle";
    /**
     * 返回要显示的view（带webview，标题等）
     * @return
     */
     View getLayout();

    /**
     * 配置webview
     */
     void setWebViewSetting();

    /**
     * 带到model里的参数
     * @param bundle
     */
     void setData(Bundle bundle);

    /**返回参数
     * @return 返回参数
     */
     Bundle getData();

    /**
     * 加载URl
     * @param url
     */
     void loadUrl(String url);

     String getUrl();

    /**
     * 返回键点击
     * @return
     */
     boolean clickBack();

    /**
     * 释放缓存
     */
     void destroy();

    /**
     * @param callback fragment 的回调
     */
    void setModelCallback(IModelCallBack callback);

    /**
     * @return 与fragment的回调钩子
     */
    IModelCallBack getModelCallback();

    //-------------------------------------以下是js交互---------------------------------------------

     void addJavascriptInterface();

    /**
     * JS接口名称，方法类请自行命名（与h5调用统一）,并记得加上@JavascriptInterface
     * @return 返回""或是null 表示不需要js交互，默认不开启交互
     */
     String getJsName();

    /**
     * 默认的js回调类
     * @param json js回调的json串
     * @return
     */
    @JavascriptInterface
     String jsCallBack(String json);

    //----------------------------------------以下是拓展方法类-----------------------------------------

    /**
     * 如果以上方法不够用，更多方法将会在这些回调回来
     */
     void callOtherMethod();
}
