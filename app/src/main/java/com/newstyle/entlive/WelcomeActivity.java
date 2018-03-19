package com.newstyle.entlive;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.newstyle.entlive.base.BaseActivity;

/**
 * Created by wangdong on 2018/3/13.
 */

public class WelcomeActivity extends BaseActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setNeedGoneNavigationBar(true);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_welcome_layout);
    }

    private void gotoMainPage(){
        //跳转到主页面
    }

}
