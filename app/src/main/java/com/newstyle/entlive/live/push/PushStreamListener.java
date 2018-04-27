package com.newstyle.entlive.live.push;

import retrofit2.http.PUT;

/**
 * Created by wangdong on 2018/4/18.
 */

public interface PushStreamListener {

    //错误状态
    public static final int ER_SYSTEM_ERROR = 0;
    public static final int ER_SDK_ERROR = 1;

    //推流时的状态
    public static final int INFO_PREVIEW_STARTED = 1;
    public static final int INFO_PREVIEW_STOPED = 2;
    public static final int INFO_PUSH_STARTED = 3;
    public static final int INFO_PUSH_RESUMED = 4;
    public static final int INFO_PUSH_PAUESED = 5;
    public static final int INFO_PUSH_STOPED = 6;
    public static final int INFO_PUSH_RESTARTED = 7;
    public static final int INFO_FIRST_FRAME_PREVIEWED = 8;

    //推流时网络状态
    public static final int NET_WORK_POOR = 1;//网络弱
    public static final int NET_WORK_RECOVERY = 2;//网络恢复
    public static final int NET_WORK_REC_START = 3;//网络开始重连
    public static final int NET_WORK_REC_FAIL = 4;//网络重连失败
    public static final int NET_WORK_REC_SUC = 5;//网络重连成功
    public static final int NET_WORK_CONNECT_FAIL = 6;//网络连接失败
    public static final int NET_WORK_SENDDATA_FAIL = 7;//发送数据失败
    public static final int NET_WORK_AUTH_OVERDUE = 8;//URL验证失效


    /**
     * 状态更新时，（初始化后，推流成功，帧率下降）
     * @param code
     */
    void onPushInfo(int code);

    /**
     * 推流出错（开始推流失败，流中断，未知错误）
     * @param errorCode,msg
     */
    void onError(int errorCode,String msg);

    /**
     * 网络状态变化
     * @param state
     */
    void onNetworkState(int state);
}
