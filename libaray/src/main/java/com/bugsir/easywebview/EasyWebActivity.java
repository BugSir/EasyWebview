package com.bugsir.easywebview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

/**
 *@author: BUG SIR
 *@date: 2018/11/13 11:38
 *@description: 用于承载fragment
 */
public    class EasyWebActivity extends AppCompatActivity   {

    private EasyWebFragmentHelper mEasyUtil;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_web);
        mEasyUtil=EasyWebFragmentHelper.create(getIntent()).replace(getSupportFragmentManager(),R.id.flyt_easy);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (mEasyUtil.onKeyDown(keyCode,event))
        {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        mEasyUtil.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEasyUtil.destroy();
        mEasyUtil=null;
    }
}
