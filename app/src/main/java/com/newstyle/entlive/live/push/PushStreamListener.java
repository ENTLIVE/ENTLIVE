package com.newstyle.entlive.live.push;

/**
 * Created by wangdong on 2018/4/18.
 */

public interface PushStreamListener {
    /**
     * 状态更新时，（初始化后，推流成功，帧率下降）
     * @param code
     */
    void onInfo(int code);

    /**
     * 推流出错（开始推流失败，流中断，未知错误）
     * @param error
     */
    void onError(int error);
}
