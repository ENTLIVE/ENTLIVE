package com.newstyle.entlive.live.push;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newstyle.entlive.base.BaseFragment;

/**
 * Created by wangdong on 2018/4/18.
 * 推流界面
 */

public class PushStreamFragment extends BaseFragment implements PushContract.View{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setPresenter(PushContract.Presenter presenter) {

    }

}
