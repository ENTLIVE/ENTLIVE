package com.newstyle.entlive;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.newstyle.entlive.base.BaseActivity;
import com.newstyle.entlive.util.LogUtil;
import com.tencent.imsdk.TIMManager;
import com.tencent.rtmp.TXLiveBase;

/**
 * Created by wangdong on 2018/3/13.
 */

public class WelcomeActivity extends BaseActivity{

    private static final String TAG = WelcomeActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setNeedGoneNavigationBar(true);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_welcome_layout);

        String sdkver = TXLiveBase.getSDKVersionStr();
        LogUtil.log(TAG,sdkver);

    }



    private void gotoMainPage(){
        //跳转到主页面
    }

}
