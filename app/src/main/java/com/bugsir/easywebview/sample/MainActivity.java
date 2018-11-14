package com.bugsir.easywebview.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bugsir.easywebview.EasyWebActivity;
import com.bugsir.easywebview.EasyWebUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EasyWebUtil.startActivity(this,MyWebViewModel.class,EasyWebActivity.class,"www.baidu.com",null);
    }
}
