package com.newstyle.entlive.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by wangdong on 2018/3/12.
 */

public class Utils {

    private static String oldMsg;
    protected static Toast toast = null;
    private static long oneTime = 0;
    private static long twoTime = 0;

    public static void showToast(Context context, String s) {


        if (s == null || TextUtils.isEmpty(s))
            return;

        if (s.contains("failed to connect to") || s.contains("502") || s.contains("404") || s.contains("failed to connect to"))
            s = "网络不给力，请重新尝试";

        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), s, Toast.LENGTH_SHORT);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (s.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
        }
        oneTime = twoTime;
    }
}
