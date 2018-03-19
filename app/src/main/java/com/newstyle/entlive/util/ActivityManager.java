package com.newstyle.entlive.util;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by wangdong on 2018/3/12.
 */

public class ActivityManager {
    private static Stack<Activity> mActivityStack;
    private static ActivityManager mInstance;

    public ActivityManager() {

    }

    public static ActivityManager getInstance() {
        if (mInstance == null) {
            synchronized (ActivityManager.class) {
                if (mInstance == null) {
                    mInstance = new ActivityManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * add Activity to activityStack
     */
    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<>();
        }
        mActivityStack.add(activity);
    }

    /**
     * finish当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        if (mActivityStack != null) {
            Activity activity = mActivityStack.lastElement();
            finishActivity(activity);
        }
    }

    /**
     * finish 指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (mActivityStack != null && activity != null) {
            mActivityStack.remove(activity);
        }
    }

    public Activity getActivity(String activityName) {
        if (mActivityStack != null) {
            for (Activity activity : mActivityStack) {
                if (activity.getLocalClassName().contains(activityName)) {
                    return activity;
                }
            }
        }
        return null;
    }

    /**
     * finish 指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        if (mActivityStack == null) {
            return;
        }
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    public boolean findActivity(Class<?> cls) {
        if (mActivityStack == null) {
            return false;
        }
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取第一个activity
     *
     * @return
     */
    public Activity getTopActivity() {
        if (mActivityStack != null && mActivityStack.size() > 0) {
            return mActivityStack.get(mActivityStack.size() - 1);
        }
        return null;
    }

    /**
     * finish all Activity
     */
    public void finishAllActivity() {
        if (mActivityStack == null) {
            return;
        }
        try {
            for (Activity activity : mActivityStack) {
                activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mActivityStack.clear();
    }

    public void finishAllActivity(Class<?> cls) {
        if (mActivityStack == null) {
            return;
        }
        try {
            for (Activity activity : mActivityStack) {
                if (!activity.getClass().equals(cls)) {
                    activity.finish();
                    mActivityStack.remove(activity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 退出应用程序
     */
    public void appExit() {
        try {

            finishAllActivity();
            System.exit(0);

        } catch (Exception e) {
        }
    }

    public void closeActivity(Class<?> cls){
        if (mActivityStack == null) {
            return;
        }
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                if (activity != null) {
                    LogUtil.log("closeActivity",activity.getClass().getSimpleName());
                    activity.finish();
                    mActivityStack.remove(activity);
                }

            }
        }
    }
}
