package com.newstyle.entlive.util.rxbus;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.util.Log;

import com.newstyle.entlive.util.LogUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by wangdong on 2018/3/9.
 *
 * 用于模块间通信
 */

public class RxBus {
    private static volatile RxBus mInstance;
    private static final String TAG = RxBus.class.getSimpleName();
    public static boolean DEBUG = false;

    private ConcurrentHashMap<Object, List<FlowableEmitter>> flowableMap = new ConcurrentHashMap<>();

    private RxBus() {
    }

    public static RxBus getInstance() {
        if (mInstance == null) {
            synchronized (RxBus.class) {
                if (mInstance == null) {
                    mInstance = new RxBus();
                }
            }
        }
        return mInstance;
    }

    /**
     * 订阅
     * @param tag
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> Flowable<T> registerEvent(@NonNull Object tag, @NonNull Class<T> clazz) {
        FlowableOnSubscribe flowableOnSubscribe = new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter emitter) throws Exception {
                List<FlowableEmitter> emitterList = flowableMap.get(tag);
                if (emitterList == null) {
                    emitterList = new ArrayList<>();
                    flowableMap.put(tag,emitterList);
                }
                emitterList.add(emitter);
                emitter.setDisposable(new Disposable() {
                    boolean isDispost = false;
                    @Override
                    public void dispose() {
                        List<FlowableEmitter> emitterList = flowableMap.get(tag);
                        if (emitterList != null) {
                            emitterList.remove(emitter);
                        }
                        isDispost = true;
                    }

                    @Override
                    public boolean isDisposed() {
                        return isDispost;
                    }
                });
            }
        };

        Flowable flowable = Flowable.create(flowableOnSubscribe, BackpressureStrategy.BUFFER);
        return flowable;
    }

    public void postEvent(@NonNull Object tag, @NonNull Object content) {
        if (flowableMap == null) {
            return;
        }
        List<FlowableEmitter> emitterList = flowableMap.get(tag);

        if (!isEmpty(emitterList)) {
            for (FlowableEmitter emitter : emitterList) {
                if (emitter != null) {
                    emitter.onNext(content);
                }
            }
        }
    }

    private static boolean isEmpty(Collection collection) {
        return null == collection || collection.isEmpty();
    }

    public void clear() {
        for (Object obj : flowableMap.keySet()) {
            List<FlowableEmitter> subjects = flowableMap.get(obj);
            if (null != subjects) {
                subjects.clear();
            }
        }
        flowableMap.clear();
    }

}
