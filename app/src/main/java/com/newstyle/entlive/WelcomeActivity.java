package com.newstyle.entlive;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.newstyle.entlive.base.BaseActivity;
import com.newstyle.entlive.net.ApiClient;
import com.newstyle.entlive.net.BaseResult;
import com.newstyle.entlive.userinfo.UserInfo;
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
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import rx.functions.Action1;

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

        //接口调用示例
        /*HashMap<String,String> params = new HashMap<>();
        params.put("name","wangdong");
        params.put("password","1234");
        ApiClient.loginGetUserInfo(params)
                .compose(bindToLifecycle())//绑定Activity或者fragment的生命周期
                .observeOn(AndroidSchedulers.mainThread())//切换到主线程
                .subscribe(new Action1<BaseResult<UserInfo>>() {
                    @Override
                    public void call(BaseResult<UserInfo> userInfoBaseResult) {
                        //在这处理结果
                        UserInfo userInfo = userInfoBaseResult.result;
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //在这处理异常
                    }
                });*/

        //演示异步操作示例
        /*Observable.create(new ObservableOnSubscribe<UserInfo>() {
            @Override
            public void subscribe(ObservableEmitter<UserInfo> emitter) throws Exception {
                //执行你的异步操作，并返回例如UserInfo等结果
                UserInfo userInfo = null;
                //userInfo = 异步操作;
                emitter.onNext(userInfo);
            }
        }).subscribeOn(Schedulers.io())//指定subscribe方法执行在IO线程
        .observeOn(AndroidSchedulers.mainThread())//指定返回结果处理方法accept执行在主线程
        .subscribe(new Consumer<UserInfo>() {
            @Override
            public void accept(UserInfo userInfo) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });*/

    }



    private void gotoMainPage(){
        //跳转到主页面
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
