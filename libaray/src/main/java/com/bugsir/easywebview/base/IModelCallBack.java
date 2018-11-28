package com.bugsir.easywebview.base;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 *@author: BUG SIR
 *@date: 2018/11/28 14:12
 *@description: 用于Model回调activity的方法
 */
public interface IModelCallBack {
    Activity getModelActivity();
    Fragment getModelFragment();
}
