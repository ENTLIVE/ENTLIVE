package com.newstyle.entlive.live.push;

import com.newstyle.entlive.util.rxbus.RxBus;

/**
 * Created by wangdong on 2018/4/18.
 * 这个类从逻辑上来说，更像是推流模块的P层
 */

public class PushStreamViewManager implements PushStreamListener{

    private IPushStreamView mPushStreamView;

    public PushStreamViewManager(IPushStreamView streamView){
        this.mPushStreamView = streamView;

        initSetting();
    }

    /**
     * 初始化设置
     */
    private void initSetting(){
        mPushStreamView.setStreamListener(this);
        mPushStreamView.setCameraFacing(0);
        mPushStreamView.setTargetVideoSize(IPushStreamView.VIDEO_SIZE_854_480P);
        //.......
    }

    /**
     * 开始推流
     * @param url
     */
    public void startPushStream(String url){

    }

    /**
     * 停止推流
     */
    public void stopPushStream(){

    }

    /**
     * 设置声音
     * @param volume
     */
    public void setVoiceVolume(float volume){

    }

    /**
     * 设置是否美颜
     * @param isOpen
     */
    public void setIsOpenBeauty(boolean isOpen){

    }

    @Override
    public void onInfo(int code) {
        //根据事件类型，发送事件通知
        RxBus.getInstance().postEvent("事件类型",null);
    }

    @Override
    public void onError(int error) {

    }
}
