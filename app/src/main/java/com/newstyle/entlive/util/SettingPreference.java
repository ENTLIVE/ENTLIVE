package com.newstyle.entlive.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.newstyle.entlive.net.NetConfig;

/**
 * Created by wangdong on 2018/3/16.
 */

public class SettingPreference {

    private static final String USER_SETTING = "user_setting";
    // 是否强制调试模式
    private static final String IS_FORCE_DEBUG = "is_force_debug";
    //是否打开日志打印开关
    private static final String IS_OPEN_LOG = "is_open_log";
    //当前环境
    private static final String CURRENT_ENV = "current_env";


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

    /**
     * 设置是否打开log
     * @param context
     * @param isOpen
     */
    public static void saveOpenLog(Context context, boolean isOpen) {
        SharedPreferences.Editor editor = getUserSPSetting(context).edit();
        editor.putBoolean(IS_OPEN_LOG,isOpen);
        editor.commit();
    }

    /**
     * 得到是否打开log
     * @param context
     * @return
     */
    public static boolean getOpenLog(Context context) {
        return getUserSPSetting(context).getBoolean(IS_OPEN_LOG,false);
    }

    /**
     * 设置当前环境
     *
     * @param context
     * @param environment
     */
    public static void setCurrentEnv(Context context, NetConfig.Environment environment) {
        SharedPreferences.Editor editor = getUserSPSetting(context).edit();
        editor.putString(CURRENT_ENV, environment.name());
        editor.commit();
    }

    /**
     * 获取当前环境
     *
     * @param context
     * @return
     */
    public static NetConfig.Environment getCurrentEnv(Context context) {
        SharedPreferences settings = getUserSPSetting(context);
        String name = settings.getString(CURRENT_ENV, null);
        if (name != null) {
            for (NetConfig.Environment environment : NetConfig.Environment.values()) {
                if (environment.name().equals(name)) {
                    return environment;
                }
            }
        }
        // 如果都没匹配到或没修改过，则返回当前环境值
        return NetConfig.sEnvironment;
    }

}
