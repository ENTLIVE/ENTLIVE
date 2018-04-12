package com.newstyle.entlive.util.rxbus;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by wangdong on 2018/4/12.
 */

public abstract class RxBusSubscriber<T> extends ResourceSubscriber<T> {

    @Override
    public void onComplete() {

    }
}
