package com.bugsir.easywebview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bugsir.easywebview.base.IBaseFragmentImpl;
import com.bugsir.easywebview.base.IBaseModelImpl;

import static com.bugsir.easywebview.base.IBaseModelImpl.INTENT_MODEL_CLASS_NAME;
import static com.bugsir.easywebview.base.IBaseModelImpl.INTENT_WEBVIEW_URL;

/**
 *@author: BUG SIR
 *@date: 2018/11/13 14:00
 *@description: 
 */
public    class EasyWebFragment extends Fragment implements IBaseFragmentImpl {
    private Bundle mBundle;
    private String mUrl;
    private IBaseModelImpl mModel;
    private View mMainView;
    public static EasyWebFragment getInstance(Class<? extends IBaseModelImpl> modelClass, String url, Bundle bundle)
    {
        EasyWebFragment fragment = new EasyWebFragment();
        if (bundle == null)
        {
            bundle = new Bundle();
        }
        if (modelClass == null)
        {
            bundle.putString(INTENT_MODEL_CLASS_NAME, IBaseModelImpl.class.getName());
        } else
        {
            bundle.putString(INTENT_MODEL_CLASS_NAME, modelClass.getName());
        }
        bundle.putString(INTENT_WEBVIEW_URL, url);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mBundle = getArguments();
        mUrl = mBundle.getString(INTENT_WEBVIEW_URL);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try
        {
            Class<?> modelName = Class.forName(mBundle.getString(INTENT_MODEL_CLASS_NAME));
//            Constructor<?> con = modelName.getConstructor();
            mModel = (IBaseModelImpl) modelName.newInstance();
        } catch (Exception e)
        {
            Toast.makeText(getContext(),"error",Toast.LENGTH_SHORT).show();
            return null;
        }
        mModel.setBundle(mBundle);
        mMainView=mModel.getLayout(getActivity());
        mModel.setWebViewSetting();
        mModel.addJavascriptInterface();
        mModel.callOtherMethod(getActivity());
        mModel.loadUrl(mUrl);
        return mMainView;
    }

    @Override
    public boolean clickBack() {
        if (mModel == null)
        {
            return true;
        }
        return mModel.clickBack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mModel!=null)
        {
            mModel.destroy();
        }
    }
}
