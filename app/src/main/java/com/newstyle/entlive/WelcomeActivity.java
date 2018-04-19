package com.newstyle.entlive;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.newstyle.entlive.base.BaseActivity;
import com.newstyle.entlive.net.ApiClient;
import com.newstyle.entlive.util.LogUtil;
import com.newstyle.entlive.util.rxbus.RxBus;
import com.newstyle.entlive.util.rxbus.RxBusSubscriber;
import com.tencent.imsdk.TIMManager;
import com.tencent.rtmp.TXLiveBase;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.HashMap;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

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

        Button sendMsg = (Button)findViewById(R.id.send_msg);
        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        HashMap<String,String> params = new HashMap<>();
        params.put("name","wangdong");
        params.put("password","1234");
        /*ApiClient.loginGetUserInfo(params)
                .compose()
                .subscribe()*/

    }



    private void gotoMainPage(){
        //跳转到主页面
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
