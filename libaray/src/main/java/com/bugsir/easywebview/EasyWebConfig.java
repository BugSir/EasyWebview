package com.bugsir.easywebview;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.bugsir.easywebview.base.IBaseModelImpl;

/**
 *@author: BUG SIR
 *@date: 2018/12/6 17:33
 *@description: 配置一些全局通用，偷懒用
 */
public    class EasyWebConfig   {
    private Class<?extends IBaseModelImpl> mModelClass=IBaseModelImpl.class;
    private Class<?extends Activity> mWebActivityClass=EasyWebActivity.class;
    private Class<?extends Fragment> mWebFragmentClass=EasyWebFragment.class;
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
    public EasyWebConfig init(Class<?extends IBaseModelImpl> mModelClass,Class<?extends Activity> mWebActivityClass,Class<?extends Fragment> mWebFragmentClass)
    {
        this.mModelClass=mModelClass;
        this.mWebActivityClass=mWebActivityClass;
        this.mWebFragmentClass=mWebFragmentClass;
        return this;
    }

    public EasyWebConfig setWebFragmentClass(Class<?extends Fragment> mWebFragmentClass)
    {
        this.mWebFragmentClass=mWebFragmentClass;
        return this;
    }

    public Class<?extends Fragment> getWebFragmentClass()
    {
        return this.mWebFragmentClass;
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
