package com.bugsir.easywebview.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 *@author: BUG SIR
 *@date: 2018/11/13 10:54
 *@description: 基础配置类接口
 */
public abstract    class BaseModel<T>  implements IBaseModelImpl {


    /**
     * 界面UI
     */
    protected View mMainView;

    /**
     * webview
     */
    protected T mWebView;

    protected Bundle mBundle;

    @Override
    public void setBundle(Bundle bundle) {
        this.mBundle=bundle;
    }

    @Override
    public String jsCallBack(String json) {
        return null;
    }

    @Override
    public void callOtherMethod(Activity activity) {

    }
    //--------------------其它拓展类--------------------
    protected  boolean isSetWebCache()
    {
        return false;
    }
}
