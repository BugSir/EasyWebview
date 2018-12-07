package com.bugsir.easywebview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;

import com.bugsir.easywebview.base.IBaseFragmentImpl;

import java.lang.ref.WeakReference;

import static com.bugsir.easywebview.base.IBaseModelImpl.INTENT_MODEL_CLASS_NAME;
import static com.bugsir.easywebview.base.IBaseModelImpl.INTENT_WEBVIEW_BUNDLE;
import static com.bugsir.easywebview.base.IBaseModelImpl.INTENT_WEBVIEW_URL;

/**
 * @author: BUG SIR
 * @date: 2018/11/13 17:38
 * @description: fragment 创建帮助类
 */
public class EasyWebFragmentHelper {

    private WeakReference<Fragment> mEasyWebWeak;

    private EasyWebFragmentHelper(Intent intent, Class<? extends Fragment> fragment) {
        try {
            mEasyWebWeak = new WeakReference<>(fragment.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bundle bundle = intent.getBundleExtra(INTENT_WEBVIEW_BUNDLE);
        if (bundle == null) {
            bundle = new Bundle();
        }
        String stringExtra = intent.getStringExtra(INTENT_WEBVIEW_URL);
        if (stringExtra != null) {
            bundle.putString(INTENT_WEBVIEW_URL, stringExtra);
        }
        stringExtra = intent.getStringExtra(INTENT_MODEL_CLASS_NAME);
        if (stringExtra != null) {
            bundle.putString(INTENT_MODEL_CLASS_NAME, stringExtra);
        }
        if (mEasyWebWeak.get() != null) {
            mEasyWebWeak.get().setArguments(bundle);
        }
    }

    public static EasyWebFragmentHelper create(Intent intent) {
        return new EasyWebFragmentHelper(intent,EasyWebFragment.class);
    }

    public static EasyWebFragmentHelper create(Intent intent,Class<?extends Fragment> fragment) {
        return new EasyWebFragmentHelper(intent,fragment);
    }

    public EasyWebFragmentHelper replace(FragmentManager fragmentManager, int id) {
        if (mEasyWebWeak.get() != null)
            fragmentManager.beginTransaction().replace(id, mEasyWebWeak.get()).commit();
        return this;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mEasyWebWeak.get() != null) {
                if (mEasyWebWeak.get() instanceof IBaseFragmentImpl) {
                    if (((IBaseFragmentImpl) mEasyWebWeak.get()).clickBack()) {
                        mEasyWebWeak.get().getActivity().finish();
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mEasyWebWeak.get() != null) {
            mEasyWebWeak.get().onActivityResult(requestCode, resultCode, data);
        }
    }

    public void destroy() {
        if (mEasyWebWeak != null) {
            mEasyWebWeak.clear();
            mEasyWebWeak = null;
        }
    }

}