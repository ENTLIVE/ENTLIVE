package com.newstyle.entlive.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newstyle.entlive.Appli;
import com.newstyle.entlive.R;
import com.newstyle.entlive.util.Utils;
import com.trello.rxlifecycle2.components.support.RxFragment;


/**
 * Created by dong.wang on 2017/7/18.
 */

public class BaseFragment extends RxFragment {

    protected int[] mRefreshCircleColors;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRefreshCircleColors = getResources().getIntArray(R.array.colors_refresh);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //ButterKnife.bind(this, view);
    }


    /**
     * 显示toast
     * @param resouceId
     */
    public void showToast(int resouceId){
        String txt = getString(resouceId);
        showToast(txt);
    }
    /**
     * 短时间显示Toast
     *
     * @param info 显示的内容
     */
    public void showToast(String info) {
        Context context = Appli.getContext();
        // 防止未Attach到界面上时，getActivity为空
        if (context != null) {
            Utils.showToast(context, info);
        }
    }

    /**
     * 启动activity
     * @param pClass
     */
    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    /**
     * 启动activity
     * @param pClass
     * @param pBundle
     */
    public void openActivity(Class<?> pClass, Bundle pBundle) {
        FragmentActivity fragmentActivity = getActivity();
        if (fragmentActivity != null) {
            Intent intent = new Intent(fragmentActivity, pClass);
            if (pBundle != null) {
                intent.putExtras(pBundle);
            }
            startActivity(intent);
        }
    }

}
