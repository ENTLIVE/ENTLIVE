package com.newstyle.entlive.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by wangdong on 2018/3/12.
 */

public class LogUtil {

    public static void log(String tag, String msg) {
        if (TextUtils.isEmpty(msg)) {
            msg = "";
        }
        /*if (Appli.getContext() != null) {
            boolean isCouldOpen = SettingPreference.getOpenLog(Appli.getContext());
            if (isCouldOpen || sIsDebugMode) {
                Log.e(tag, msg);
            }
        }*/
    }

    public static void logLocal(String tag, String msg) {
        /*if (Appli.getContext() != null && msg != null) {
            boolean isCouldOpen = SettingPreference.getOpenLog(Appli.getContext());
            if (isCouldOpen || sIsDebugMode) {
                Log.d(tag, msg);
            }
        }*/
    }

}
