package com.newstyle.entlive.live.push;

import android.view.SurfaceView;

import com.alivc.live.pusher.AlivcAudioAACProfileEnum;
import com.alivc.live.pusher.AlivcBeautyLevelEnum;
import com.alivc.live.pusher.AlivcFpsEnum;
import com.alivc.live.pusher.AlivcLivePushConfig;
import com.alivc.live.pusher.AlivcLivePushError;
import com.alivc.live.pusher.AlivcLivePushErrorListener;
import com.alivc.live.pusher.AlivcLivePushInfoListener;
import com.alivc.live.pusher.AlivcLivePushNetworkListener;
import com.alivc.live.pusher.AlivcLivePushStats;
import com.alivc.live.pusher.AlivcLivePusher;
import com.alivc.live.pusher.AlivcPreviewOrientationEnum;
import com.alivc.live.pusher.AlivcQualityModeEnum;
import com.alivc.live.pusher.AlivcResolutionEnum;
import com.newstyle.entlive.Appli;

/**
 * Created by wangdong on 2018/4/26.
 */

public class AliPushStreamComentView implements IPushStreamView {

    private AlivcLivePushConfig mAlivcLivePushConfig;
    private AlivcLivePusher mAlivcLivePusher;
    private PushStreamListener mPushStreamListener;
    private SurfaceView mPreShowView;
    private String mPushUrl;


    public AliPushStreamComentView(SurfaceView surfaceView){
        this.mPreShowView = surfaceView;
        initConfig();//初始化配置
        mAlivcLivePusher = new AlivcLivePusher();
        mAlivcLivePusher.init(Appli.getContext(), mAlivcLivePushConfig);
        setPusherListener();
    }

    private void initConfig(){
        mAlivcLivePushConfig = new AlivcLivePushConfig();
        mAlivcLivePushConfig.setResolution(AlivcResolutionEnum.RESOLUTION_540P);
        mAlivcLivePushConfig.setFps(AlivcFpsEnum.FPS_20); //建议用户使用20fps
        mAlivcLivePushConfig.setEnableBitrateControl(true); // 打开码率自适应，默认为true
        mAlivcLivePushConfig.setPreviewOrientation(AlivcPreviewOrientationEnum.ORIENTATION_PORTRAIT); // 默认为竖屏，可设置home键向左或向右横屏。
        mAlivcLivePushConfig.setAudioProfile(AlivcAudioAACProfileEnum.AAC_LC);//设置音频编码模式
        mAlivcLivePushConfig.setEnableBitrateControl(true);// 打开码率自适应，默认为true
        mAlivcLivePushConfig.setQualityMode(AlivcQualityModeEnum.QM_RESOLUTION_FIRST);
        mAlivcLivePushConfig.setEnableAutoResolution(true);
    }

    private void setPusherListener(){
        mAlivcLivePusher.setLivePushErrorListener(new AlivcLivePushErrorListener(){

            @Override
            public void onSystemError(AlivcLivePusher alivcLivePusher, AlivcLivePushError alivcLivePushError) {
                if (mPushStreamListener != null && alivcLivePushError != null) {
                    mPushStreamListener.onError(alivcLivePushError.getCode(),alivcLivePushError.getMsg());
                }
            }

            @Override
            public void onSDKError(AlivcLivePusher alivcLivePusher, AlivcLivePushError alivcLivePushError) {
                if (mPushStreamListener != null && alivcLivePushError != null) {
                    mPushStreamListener.onError(alivcLivePushError.getCode(),alivcLivePushError.getMsg());
                }
            }
        });
        mAlivcLivePusher.setLivePushInfoListener(new AlivcLivePushInfoListener(){

            @Override
            public void onPreviewStarted(AlivcLivePusher alivcLivePusher) {
                if (mPushStreamListener != null) {
                    mPushStreamListener.onPushInfo(PushStreamListener.INFO_PREVIEW_STARTED);
                }
            }

            @Override
            public void onPreviewStoped(AlivcLivePusher alivcLivePusher) {
                if (mPushStreamListener != null) {
                    mPushStreamListener.onPushInfo(PushStreamListener.INFO_PREVIEW_STOPED);
                }
            }

            @Override
            public void onPushStarted(AlivcLivePusher alivcLivePusher) {
                if (mPushStreamListener != null) {
                    mPushStreamListener.onPushInfo(PushStreamListener.INFO_PREVIEW_STOPED);
                }
            }

            @Override
            public void onPushPauesed(AlivcLivePusher alivcLivePusher) {
                if (mPushStreamListener != null) {
                    mPushStreamListener.onPushInfo(PushStreamListener.INFO_PUSH_PAUESED);
                }
            }

            @Override
            public void onPushResumed(AlivcLivePusher alivcLivePusher) {
                if (mPushStreamListener != null) {
                    mPushStreamListener.onPushInfo(PushStreamListener.INFO_PUSH_RESUMED);
                }
            }

            @Override
            public void onPushStoped(AlivcLivePusher alivcLivePusher) {
                if (mPushStreamListener != null) {
                    mPushStreamListener.onPushInfo(PushStreamListener.INFO_PUSH_STOPED);
                }
            }

            @Override
            public void onPushRestarted(AlivcLivePusher alivcLivePusher) {
                if (mPushStreamListener != null) {
                    mPushStreamListener.onPushInfo(PushStreamListener.INFO_PUSH_RESTARTED);
                }
            }

            @Override
            public void onFirstFramePreviewed(AlivcLivePusher alivcLivePusher) {
                if (mPushStreamListener != null) {
                    mPushStreamListener.onPushInfo(PushStreamListener.INFO_FIRST_FRAME_PREVIEWED);
                }
            }

            @Override
            public void onDropFrame(AlivcLivePusher alivcLivePusher, int i, int i1) {

            }

            @Override
            public void onAdjustBitRate(AlivcLivePusher alivcLivePusher, int i, int i1) {

            }

            @Override
            public void onAdjustFps(AlivcLivePusher alivcLivePusher, int i, int i1) {

            }
        });

        mAlivcLivePusher.setLivePushNetworkListener(new AlivcLivePushNetworkListener() {

            @Override
            public void onNetworkPoor(AlivcLivePusher alivcLivePusher) {
                if (mPushStreamListener != null) {
                    mPushStreamListener.onNetworkState(PushStreamListener.NET_WORK_POOR);
                }
            }

            @Override
            public void onNetworkRecovery(AlivcLivePusher alivcLivePusher) {
                if (mPushStreamListener != null) {
                    mPushStreamListener.onNetworkState(PushStreamListener.NET_WORK_RECOVERY);
                }
            }

            @Override
            public void onReconnectStart(AlivcLivePusher alivcLivePusher) {
                if (mPushStreamListener != null) {
                    mPushStreamListener.onNetworkState(PushStreamListener.NET_WORK_REC_START);
                }
            }

            @Override
            public void onReconnectFail(AlivcLivePusher alivcLivePusher) {
                if (mPushStreamListener != null) {
                    mPushStreamListener.onNetworkState(PushStreamListener.NET_WORK_REC_FAIL);
                }
            }

            @Override
            public void onReconnectSucceed(AlivcLivePusher alivcLivePusher) {
                if (mPushStreamListener != null) {
                    mPushStreamListener.onNetworkState(PushStreamListener.NET_WORK_REC_SUC);
                }
            }

            @Override
            public void onSendDataTimeout(AlivcLivePusher alivcLivePusher) {
                if (mPushStreamListener != null) {
                    mPushStreamListener.onNetworkState(PushStreamListener.NET_WORK_SENDDATA_FAIL);
                }
            }

            @Override
            public void onConnectFail(AlivcLivePusher alivcLivePusher) {
                if (mPushStreamListener != null) {
                    mPushStreamListener.onNetworkState(PushStreamListener.NET_WORK_CONNECT_FAIL);
                }
            }

            @Override
            public String onPushURLAuthenticationOverdue(AlivcLivePusher alivcLivePusher) {
                if (mPushStreamListener != null) {
                    mPushStreamListener.onNetworkState(PushStreamListener.NET_WORK_AUTH_OVERDUE);
                }
                return null;
            }

            @Override
            public void onSendMessage(AlivcLivePusher alivcLivePusher) {

            }
        });
    }


    @Override
    public void setStreamListener(PushStreamListener streamListener) {
        this.mPushStreamListener = streamListener;
    }

    @Override
    public void setEncodeMethod(int encodeMethod) {

    }

    @Override
    public void setCameraFacing(int cameraFacing) {

    }

    @Override
    public void startPreview() {
        mAlivcLivePusher.startPreview(mPreShowView);
    }

    @Override
    public void setStreamUrl(String streamUrl) {
        this.mPushUrl = streamUrl;
    }

    @Override
    public void startStreaming() {
        if (mPushUrl == null) {
            throw new RuntimeException("mPushUrl can not is null,please first call setStreamUrl");
        }
        mAlivcLivePusher.startPush(mPushUrl);
    }

    @Override
    public void stopStreaming() {
        mAlivcLivePusher.stopPush();
    }

    @Override
    public void setBeautyIsOpen(boolean isOpen) {
        if (isOpen) {
            mAlivcLivePushConfig.setBeautyOn(true); // 开启美颜
            mAlivcLivePushConfig.setBeautyLevel(AlivcBeautyLevelEnum.BEAUTY_Normal);//设定为基础美颜
            mAlivcLivePushConfig.setBeautyWhite(70);// 美白范围0-100
            mAlivcLivePushConfig.setBeautyBuffing(40);// 磨皮范围0-100
            mAlivcLivePushConfig.setBeautyRuddy(40);// 红润设置范围0-100
        } else {
            mAlivcLivePushConfig.setBeautyOn(false);
        }
    }

    @Override
    public void onResume() {
        if (mAlivcLivePusher.getCurrentStatus() == AlivcLivePushStats.PUSHED) {
            mAlivcLivePusher.resume();
        }
    }

    @Override
    public void onPause() {
        if (mAlivcLivePusher.getCurrentStatus() == AlivcLivePushStats.PUSHING) {
            mAlivcLivePusher.pause();
        }
    }

    @Override
    public void release() {
        mAlivcLivePusher.stopPush();
        mAlivcLivePusher.destroy();
    }
}
