package com.newstyle.entlive.live.push;

import com.newstyle.entlive.util.LogUtil;
import com.newstyle.entlive.util.rxbus.RxBus;

/**
 * Created by wangdong on 2018/4/18.
 * 这个类从逻辑上来说，更像是推流模块的P层
 */

public class PushStreamViewManager implements PushStreamListener{

    private static final String LOG = PushStreamViewManager.class.getSimpleName();

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

        //.......
    }

    /**
     * 开始预览
     */
    public void startPreview(){
        mPushStreamView.startPreview();
    }

    /**
     * 开始推流
     * @param url
     */
    public void startPushStream(String url){
        mPushStreamView.setStreamUrl(url);
        mPushStreamView.startStreaming();
    }

    /**
     * 停止推流
     */
    public void stopPushStream(){
        mPushStreamView.stopStreaming();
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
        mPushStreamView.setBeautyIsOpen(isOpen);
    }

    @Override
    public void onPushInfo(int code) {
        //根据事件类型，发送事件通知
        //RxBus.getInstance().postEvent("事件类型",null);
        LogUtil.logLocal(LOG,"onPushInfo-code: "+code);
    }

    @Override
    public void onError(int errorCode,String msg) {
        LogUtil.logLocal(LOG,"onError-code: "+errorCode+"; msg: "+msg);
    }

    @Override
    public void onNetworkState(int state) {
        LogUtil.logLocal(LOG,"onNetworkState-state: "+state);
    }

    /**
     * 同步fragment的生命周期
     */
    public void onCreate(){

    }

    public void onResume(){
        mPushStreamView.onResume();
    }



    public void onPause(){
        mPushStreamView.onPause();
    }

    public void onDestroy(){
        mPushStreamView.release();
    }
}
