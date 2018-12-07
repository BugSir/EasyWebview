package com.bugsir.easywebview.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bugsir.easywebview.EasyWebActivity;
import com.bugsir.easywebview.EasyWebConfig;
import com.bugsir.easywebview.EasyWebUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EasyWebConfig.getInstance().init(MyWebViewModel.class,EasyWebActivity.class);
        EasyWebUtil.startActivity(this,"www.baidu.com",null);
    }
}
