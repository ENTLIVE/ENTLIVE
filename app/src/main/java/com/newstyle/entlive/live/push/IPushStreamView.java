package com.newstyle.entlive.live.push;

/**
 * Created by wangdong on 2018/4/18.
 * 定义推流接口，隔离底层第三方框架的具体方法
 */

public interface IPushStreamView {
    //
    public static final int VIDEO_SIZE_1280_720P = 1;

    public static final int VIDEO_SIZE_854_480P = 2;

    public static final int VIDEO_SIZE_640_360P = 3;

    /**
     * 设置推流错误回调
     * @param pushStreamViewManager
     */
    void setStreamListener(PushStreamViewManager pushStreamViewManager);

    /**
     * 设置预览帧率
     * @param fps
     */
    void setPreviewFps(float fps);

    /**
     * 设置推流帧率
     * @param fps
     */
    void setTargetFps(float fps);

    /**
     * 设置视频码率
     * @param kBitrate
     */
    void setVideoKBitrate(int kBitrate);

    /**
     * 设置视频的
     * @param size @reference(VIDEO_SIZE_1280_720P,VIDEO_SIZE_854_480P,VIDEO_SIZE_640_360P)
     */
    void setTargetVideoSize(int size);

    /**
     * 设置软硬解
     * @param encodeMethod
     */
    void setEncodeMethod(int encodeMethod);

    /**
     * 设置哪个摄像头
     * @param cameraFacing
     */
    void setCameraFacing(int cameraFacing);

    /**
     * 设置推流地址
     * @param streamUrl
     */
    void setStreamUrl(String streamUrl);

    /**
     * 开始推流
     */
    void startStreaming();

    /**
     * 停止推流
     */
    void stopStreaming();

    /**
     * 回到前台，显示出来(可以做一些，恢复推流和恢复声音)
     */
    void onResume();

    /**
     * 进入后台，（可以做一些暂停推流和静音操作）
     */
    void onPause();

    /**
     * 释放资源
     */
    void release();

    //暂时想到这些接口,在以后开发中，可以进行添加
}
