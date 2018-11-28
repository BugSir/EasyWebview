package com.bugsir.easywebview.base;

import android.os.Bundle;
import android.view.View;

/**
 * @author: BUG SIR
 * @date: 2018/11/13 10:54
 * @description: 基础配置类接口
 */
public abstract class BaseModel<T> implements IBaseModelImpl {


    /**
     * 界面UI
     */
    protected View mMainView;

    /**
     * webview
     */
    protected T mWebView;

    protected Bundle mBundle;

    public String mUrl;

    public IModelCallBack callBack;

    @Override
    public void setData(Bundle bundle) {
        this.mBundle = bundle;
    }

    @Override
    public Bundle getData() {
        return mBundle;
    }

    @Override
    public String getUrl() {
        return mUrl;
    }

    @Override
    public void setModelCallback(IModelCallBack callback) {
        this.callBack = callback;
    }

    @Override
    public IModelCallBack getModelCallback() {
        return callBack;
    }

    @Override
    public String jsCallBack(String json) {
        return null;
    }

    @Override
    public void callOtherMethod() {

    }

    @Override
    public void loadUrl(String url) {
        this.mUrl = url;
    }

    //--------------------其它拓展类--------------------
    protected boolean isSetWebCache() {
        return false;
    }
}
