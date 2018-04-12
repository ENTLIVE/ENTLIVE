package com.newstyle.entlive.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import android.util.Log;

import com.newstyle.entlive.Appli;

/**
 * Created by wangdong on 2018/3/12.
 */

public class LogUtil {

    private static boolean sIsDebugMode = true;

    /**
     * 判断是否是debug模式
     *
     * @param context
     * @return
     */
    public static void checkDebugMode(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        sIsDebugMode = (applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        sIsDebugMode |= SettingPreference.isForceDebug(context);    // 强制调试
    }

    /**
     * 判断是否调试模式
     *
     * @return
     */
    public static boolean isDebugMode() {
        return sIsDebugMode;
    }

    /**
     * log.e
     * @param tag
     * @param msg
     */
    public static void log(String tag, String msg) {
        if (TextUtils.isEmpty(msg)) {
            msg = "";
        }
        if (Appli.getContext() != null) {
            boolean isCouldOpen = SettingPreference.getOpenLog(Appli.getContext());
            if (isCouldOpen || sIsDebugMode) {
                Log.e(tag, msg);
            }
        }
    }

    /**
     * log.d
     * @param tag
     * @param msg
     */
    public static void logLocal(String tag, String msg) {
        if (Appli.getContext() != null && msg != null) {
            boolean isCouldOpen = SettingPreference.getOpenLog(Appli.getContext());
            if (isCouldOpen || sIsDebugMode) {
                Log.d(tag, msg);
            }
        }
    }

}
