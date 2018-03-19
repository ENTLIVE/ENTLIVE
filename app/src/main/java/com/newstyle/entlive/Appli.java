package com.newstyle.entlive;

import android.app.Application;
import android.content.Context;

import com.newstyle.entlive.util.LogUtil;

/**
 * Created by wangdong on 2018/3/12.
 */

public class Appli extends Application {

    private static Application mContext;

    public Appli(){
        super();
       mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.checkDebugMode(this);
    }
}
