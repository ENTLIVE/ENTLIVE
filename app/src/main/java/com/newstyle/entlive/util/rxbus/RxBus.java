package com.newstyle.entlive.util.rxbus;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
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
    private ConcurrentHashMap<Object, List<Subject>> subjectMapper = new ConcurrentHashMap<>();

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
     * 发送事件
     */
    public void post(@NonNull Object tag, @NonNull Object content) {
        if (subjectMapper == null) {
            return;
        }
        List<Subject> subjectList = subjectMapper.get(tag);

        if (!isEmpty(subjectList)) {
            for (Subject subject : subjectList) {
                if (subject != null) {
                    subject.onNext(content);
                }
            }
        }
        if (DEBUG) Log.d(TAG, "[send]subjectMapper: " + subjectMapper);
    }


    /**
     * 订阅
     * @param tag
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> Observable<T> register(@NonNull Object tag, @NonNull Class<T> clazz) {
        List<Subject> subjectList = subjectMapper.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList<>();
            subjectMapper.put(tag, subjectList);
        }

        Subject<T> subject;
        subjectList.add(subject = PublishSubject.create());
        if (DEBUG) Log.d(TAG, "[register]subjectMapper: " + subjectMapper);
        return subject;
    }

    /**
     * 取消订阅
     * @param tag
     * @param observable
     */
    public void unregister(@NonNull Object tag, @NonNull Observable observable) {
        List<Subject> subjects = subjectMapper.get(tag);
        if (null != subjects) {
            subjects.remove(observable);
            if (isEmpty(subjects)) {
                subjectMapper.remove(tag);
            }
            if (!observable.subscribe().isDisposed()) {
                observable.subscribe().dispose();
            }
        }
        if (DEBUG) Log.d(TAG, "[unregister]subjectMapper: " + subjectMapper);
    }

    private static boolean isEmpty(Collection collection) {
        return null == collection || collection.isEmpty();
    }

    public void clear() {
        for (Object obj : subjectMapper.keySet()) {
            List<Subject> subjects = subjectMapper.get(obj);
            if (null != subjects) {
                subjects.clear();
            }
        }
        subjectMapper.clear();
    }

}
