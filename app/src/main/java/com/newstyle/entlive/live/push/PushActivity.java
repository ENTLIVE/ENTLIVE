package com.newstyle.entlive.live.push;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.newstyle.entlive.R;
import com.newstyle.entlive.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by wangdong on 2018/4/26.
 */

public class PushActivity extends BaseActivity {


    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.push_act_layout);


        PushStreamFragment pushStreamFragment = new PushStreamFragment();
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.content_layout, pushStreamFragment);
        transaction.commit();
    }
}
