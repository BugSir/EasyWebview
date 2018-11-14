package com.bugsir.easywebview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;

import com.bugsir.easywebview.base.IBaseModelImpl;

import java.lang.ref.WeakReference;

import static com.bugsir.easywebview.base.IBaseModelImpl.INTENT_MODEL_CLASS_NAME;
import static com.bugsir.easywebview.base.IBaseModelImpl.INTENT_WEBVIEW_BUNDLE;
import static com.bugsir.easywebview.base.IBaseModelImpl.INTENT_WEBVIEW_URL;

/**
 * @author: BUG SIR
 * @date: 2018/11/13 17:38
 * @description:
 */
public class EasyWebUtil {

    public static void startActivity(Context context,
                                     Class<? extends IBaseModelImpl> modelClass, Class<? extends Activity> webActivity, String url, Bundle bundle) {
        Intent intent = new Intent(context, webActivity==null?EasyWebActivity.class:webActivity);
        if (modelClass == null) {
            intent.putExtra(INTENT_MODEL_CLASS_NAME, IBaseModelImpl.class.getName());
        } else {
            intent.putExtra(INTENT_MODEL_CLASS_NAME, modelClass.getName());
        }
        intent.putExtra(INTENT_WEBVIEW_URL, url);
        intent.putExtra(INTENT_WEBVIEW_BUNDLE, bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    public static void startActivityForResult(Context context, Class<? extends IBaseModelImpl> modelClass, Class<? extends Activity> webActivity,
                                              String url, Bundle bundle, int requestCode) {
        Intent intent = new Intent(context, webActivity==null?EasyWebActivity.class:webActivity);
        if (modelClass == null) {
            intent.putExtra(INTENT_MODEL_CLASS_NAME, IBaseModelImpl.class.getName());
        } else {
            intent.putExtra(INTENT_MODEL_CLASS_NAME, modelClass.getName());
        }
        intent.putExtra(INTENT_WEBVIEW_URL, url);
        intent.putExtra(INTENT_WEBVIEW_BUNDLE, bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    private WeakReference<EasyWebFragment> mEasyWebWeak;

    private EasyWebUtil(Intent intent)
    {
        mEasyWebWeak=new WeakReference<>(new EasyWebFragment());
        Bundle bundle = intent.getBundleExtra(INTENT_WEBVIEW_BUNDLE);
        if (bundle == null)
        {
            bundle = new Bundle();
        }
        String stringExtra = intent.getStringExtra(INTENT_WEBVIEW_URL);
        if (stringExtra != null)
        {
            bundle.putString(INTENT_WEBVIEW_URL, stringExtra);
        }
        stringExtra = intent.getStringExtra(INTENT_MODEL_CLASS_NAME);
        if (stringExtra != null)
        {
            bundle.putString(INTENT_MODEL_CLASS_NAME, stringExtra);
        }
        if (mEasyWebWeak.get()!=null)
        {
            mEasyWebWeak.get().setArguments(bundle);
        }
    }

    public static EasyWebUtil create(Intent intent)
    {
       return new EasyWebUtil(intent);
    }

    public EasyWebUtil replace(FragmentManager fragmentManager,int id)
    {
        if (mEasyWebWeak.get()!=null)
        fragmentManager.beginTransaction().replace(id, mEasyWebWeak.get()).commit();
        return this;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if (mEasyWebWeak.get() != null)
            {
                if (mEasyWebWeak.get().clickBack())
                {
                    mEasyWebWeak.get().getActivity().finish();
                }
                return true;
            }
        }
        return false;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (mEasyWebWeak.get() != null)
        {
            mEasyWebWeak.get().onActivityResult(requestCode, resultCode, data);
        }
    }

    public void destroy()
    {
        if (mEasyWebWeak!=null)
        {
            mEasyWebWeak.clear();
            mEasyWebWeak=null;
        }
    }

}
