package com.newstyle.entlive.net;

import com.newstyle.entlive.Appli;
import com.newstyle.entlive.util.SettingPreference;

/**
 * Created by wangdong on 2018/3/21.
 */

public class NetConfig {

    public static Environment sEnvironment = Environment.PRODUCT;
    /**
     * 服务器地址
     */
    public final static String SERVER_URL;

    public enum Environment {
        /**
         * 开发环境
         */
        DEV,
        /**
         * 测试环境
         */
        TEST,
        /**
         * 正式环境
         */
        PRODUCT
    }


    static {
        NetConfig.Environment saved = SettingPreference.getCurrentEnv(Appli.getContext());
        if (NetConfig.sEnvironment != saved) {
            NetConfig.sEnvironment = saved;
        }
        switch (sEnvironment) {
            case DEV:
                SERVER_URL = "";
                break;
            case TEST:
                SERVER_URL = "";
                break;
            default:
                SERVER_URL = "";
        }
    }

    public static class Login{
        public static final String URL_LOGIN = "";
    }

}
