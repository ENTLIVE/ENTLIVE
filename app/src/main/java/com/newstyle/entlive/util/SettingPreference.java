package com.newstyle.entlive.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wangdong on 2018/3/16.
 */

public class SettingPreference {

    private static final String USER_SETTING = "user_setting";
    // 是否强制调试模式
    private static final String IS_FORCE_DEBUG = "is_force_debug";


    private static SharedPreferences getUserSPSetting(Context context){
        return context.getSharedPreferences(USER_SETTING, 0);
    }

    /**
     * 设置调试模式
     * @param context
     * @param isForceDebug
     */
    public static void setIsForceDebug(Context context, boolean isForceDebug) {
        SharedPreferences.Editor editor = getUserSPSetting(context).edit();
        editor.putBoolean(IS_FORCE_DEBUG, isForceDebug);
        editor.commit();
    }

    /**
     * 是否强制调试模式
     *
     * @param context
     * @return
     */
    public static boolean isForceDebug(Context context) {
        return getUserSPSetting(context).getBoolean(IS_FORCE_DEBUG, false);
    }

}
