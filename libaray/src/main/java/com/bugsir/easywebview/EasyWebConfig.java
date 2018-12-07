package com.bugsir.easywebview;

import android.app.Activity;

import com.bugsir.easywebview.base.IBaseModelImpl;

/**
 *@author: BUG SIR
 *@date: 2018/12/6 17:33
 *@description: 配置一些全局通用，偷懒用
 */
public    class EasyWebConfig   {
    private Class<?extends IBaseModelImpl> mModelClass=IBaseModelImpl.class;
    private Class<?extends Activity> mWebActivityClass=EasyWebActivity.class;
    private static EasyWebConfig mInstance;

    public static synchronized EasyWebConfig getInstance()
    {
        if (mInstance==null)
        {
            mInstance=new EasyWebConfig();
        }
        return mInstance;
    }

    /**
     * 初始化全局通用
     * @param mModelClass 公用model
     * @param mWebActivityClass 自定义的承载activity
     * @return
     */
    public EasyWebConfig init(Class<?extends IBaseModelImpl> mModelClass,Class<?extends Activity> mWebActivityClass)
    {
        this.mModelClass=mModelClass;
        this.mWebActivityClass=mWebActivityClass;
        return this;
    }

    public Class<?extends IBaseModelImpl> getModelClass()
    {
        return mModelClass;
    }

    public Class<?extends Activity> getWebActivityClass()
    {
        return mWebActivityClass;
    }
}
