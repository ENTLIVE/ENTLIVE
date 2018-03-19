package com.newstyle.entlive.base;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.badoo.mobile.util.WeakHandler;
import com.bumptech.glide.Glide;
import com.newstyle.entlive.R;
import com.newstyle.entlive.util.ActivityManager;
import com.newstyle.entlive.util.LogUtil;
import com.newstyle.entlive.util.Utils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by dong.wang on 2017/7/18.
 */

public class BaseActivity extends RxAppCompatActivity implements Constant {
    protected boolean mIsNeedGoneNavigationBar = false;
    protected WeakHandler mBaseHandler;
    protected int[] mColors;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        ActivityManager.getInstance().addActivity(this);
        //ButterKnife.bind(this);
        mBaseHandler = new WeakHandler();
        setStatusBarIconAndTextBlack();
        mColors = getResources().getIntArray(R.array.colors_refresh);
        mContext = this;
        LogUtil.logLocal("Activity",getClass().getName()+"-onCreate");
    }

    /**
     * 设置状态栏
     */
    protected void setStatusBarIconAndTextBlack(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !isXiaoMiPhone()) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.color_42000000));
            }
        }
    }

    public boolean isXiaoMiPhone(){
        return "Xiaomi".equalsIgnoreCase(Build.MANUFACTURER);
    }

    /**
     * 设置状态栏背景色
     * @param color
     */
    protected void setStatusBarBackground(int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //注意要清除 FLAG_TRANSLUCENT_STATUS flag
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(color);
        }
    }

    /**
     * 设置状态栏透明
     */
    protected void setStatusBarTranslucent(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
       super.setContentView(view, params);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        ActivityManager.getInstance().finishActivity(this);
        Glide.get(this).clearMemory();
        Glide.get(this).trimMemory(ComponentCallbacks2.TRIM_MEMORY_MODERATE);
        System.gc();
        super.onDestroy();
    }

    public void openActivity(Class<?> pClass) {
        this.openActivity(pClass, null);
    }

    public void openActivity(Class<?> pClass, @Nullable Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }


    /**
     * 关闭Activity
     */
    public void finish() {
        ActivityManager.getInstance().finishActivity(this);
        System.gc();
        super.finish();
        LogUtil.log("界面退出", getClass().getName());
    }

    /**
     * 关闭Activity
     */
    public void finish(int enterAnim, int exitAnim) {
        super.finish();
        overridePendingTransition(enterAnim, exitAnim);
    }


    /**
     * 设置是否隐藏导航栏
     * @param isNeed
     */
    protected void setNeedGoneNavigationBar(boolean isNeed){
        mIsNeedGoneNavigationBar  = isNeed;
        if (mIsNeedGoneNavigationBar) {
            mBaseHandler.postDelayed(mHideRunnable,200);
            final View decorView = getWindow().getDecorView();
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    decorView.requestLayout();
                }
            });
        }
    }

    // 判断权限集合
    protected boolean needPermissions(String... permissions) {
        boolean isNeed;
        for (String permission : permissions) {
            isNeed = needsPermission(permission);
            if (isNeed) {
                return true;
            }
        }
        return false;
    }

    // 判断是否缺少权限
    protected boolean needsPermission(String permission) {
        return ContextCompat.checkSelfPermission(getApplicationContext(), permission) != PackageManager.PERMISSION_GRANTED;
    }

    protected Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            int flags;
            int curApiVersion = Build.VERSION.SDK_INT;
            // This work only for android 4.4+
            if (curApiVersion >= Build.VERSION_CODES.KITKAT) {
                // This work only for android 4.4+
                // hide navigation bar permanently in android activity
                // touch the screen, the navigation bar will not show
                flags = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
//                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
            } else {
                // touch the screen, the navigation bar will show
                flags = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            }
            // must be executed in main thread :)
            getWindow().getDecorView().setSystemUiVisibility(flags);
        }
    };

    public void showToast(int resId) {
        showToast(getString(resId));
    }

    public void showToast(String info) {
        Utils.showToast(this, info);
    }

    // 隐藏键盘
    public void hideKeyBoard() {
        try {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 显示键盘
    public void showKeyBoard(View view) {
        try {
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).showSoftInput(view, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
