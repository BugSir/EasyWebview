package com.bugsir.easywebview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.bugsir.easywebview.base.IBaseModelImpl;

import static com.bugsir.easywebview.base.IBaseModelImpl.INTENT_MODEL_CLASS_NAME;
import static com.bugsir.easywebview.base.IBaseModelImpl.INTENT_WEBVIEW_BUNDLE;
import static com.bugsir.easywebview.base.IBaseModelImpl.INTENT_WEBVIEW_URL;

/**
 * @author: BUG SIR
 * @date: 2018/11/13 17:38
 * @description:
 */
public class EasyWebUtil {

    public static void startActivity(Context context, Class<? extends IBaseModelImpl> modelClass, String url, Bundle bundle) {
        startActivity(context,modelClass,EasyWebConfig.getInstance().getWebActivityClass(),url,bundle);
    }
    public static void startActivity(Context context, String url,Bundle bundle) {
        startActivity(context,EasyWebConfig.getInstance().getModelClass(),EasyWebConfig.getInstance().getWebActivityClass(),url,bundle);
    }
    public static void startActivityForResult(Context context, Class<? extends IBaseModelImpl> modelClass, String url, Bundle bundle,int requestCode) {
        startActivityForResult(context,modelClass,EasyWebConfig.getInstance().getWebActivityClass(),url,bundle,requestCode);
    }
    public static void startActivityForResult(Context context, String url,Bundle bundle,int requestCode) {
        startActivityForResult(context,EasyWebConfig.getInstance().getModelClass(),EasyWebConfig.getInstance().getWebActivityClass(),url,bundle,requestCode);
    }

    public static void startActivity(Context context, Class<? extends IBaseModelImpl> modelClass, Class<? extends Activity> webActivity, String url, Bundle bundle) {
        Intent intent = new Intent(context, webActivity == null ? EasyWebActivity.class : webActivity);
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

    public static void startActivityForResult(Context context, Class<? extends IBaseModelImpl> modelClass, Class<? extends Activity> webActivity, String url, Bundle bundle, int requestCode) {
        Intent intent = new Intent(context, webActivity == null ? EasyWebActivity.class : webActivity);
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


}
