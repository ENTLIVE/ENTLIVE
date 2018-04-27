package com.newstyle.entlive.live.push;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.newstyle.entlive.R;
import com.newstyle.entlive.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangdong on 2018/4/18.
 * 推流界面
 */

public class PushStreamFragment extends BaseFragment implements PushContract.View{

    @BindView(R.id.preview_view)
    SurfaceView mLiveStreamView;

    private PushStreamViewManager mPushStreamViewManager;
    private PushContract.Presenter mPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.push_frag_layout,container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IPushStreamView pushStreamView = new AliPushStreamComentView(mLiveStreamView);
        mPushStreamViewManager = new PushStreamViewManager(pushStreamView);
        mPushStreamViewManager.onCreate();
    }

    @Override
    public void setPresenter(PushContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPushStreamViewManager.onResume();
        mPushStreamViewManager.startPreview();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPushStreamViewManager.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPushStreamViewManager.onDestroy();
    }
}
