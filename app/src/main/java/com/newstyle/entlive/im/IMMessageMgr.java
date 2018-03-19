package com.newstyle.entlive.im;

import com.newstyle.entlive.im.mode.IMMessage;
import com.newstyle.entlive.login.LoginManager;

/**
 * Created by wangdong on 2018/3/15.
 */

public abstract class IMMessageMgr {


    /**
     * 进入IM直播间
     * @param liveRoomId
     * @param imRoomId
     */
    public void enterChatRoom(String liveRoomId, String imRoomId){
        checkLoginState(new LoginManager.LoginCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(int code, String msg) {

            }
        });
    }

    /**
     * 检查登录状态
     * @param callback
     */
    private void checkLoginState(LoginManager.LoginCallback callback){

    }

    /**
     * 发送文本型聊天消息
     */
    public void sendTextMsg(){
        IMMessage imMessage = createMsgFactory(0);
        imSendRoomMsg(imMessage);
    }

    /**
     * 发送点赞消息
     */
    public void sendPraiseMsg(){

    }

    /**
     * 发送弹幕消息
     */
    public void sendDanmuMsg(){

    }

    /**
     * 根据类型，生产相应的消息
     * @param type
     * @return
     */
    private IMMessage createMsgFactory(int type){
        IMMessage imMessage = null;

        return imMessage;
    }

//..............................以下是子类实现的方法...................................................//

    /**
     * 子类具体实现对应平台的api调用
     * @param liveRoomId
     * @param imRoomId
     */
    protected abstract void imEnterChatRoom(String liveRoomId, String imRoomId);

    /**
     * 子类实现退出直播间api调用
     */
    protected abstract void imExitChatRoom();

    /**
     * 子类具体实现对应平台的api调用
     * @param imMessage
     */
    protected abstract void imSendRoomMsg(IMMessage imMessage);

    /**
     * 禁言
     * @param muteDuration
     */
    protected abstract void imMuteMember(long muteDuration);

    /**
     * 取消禁言
     */
    protected abstract void imRemoveMuteMember();

    /**
     * 踢人
     */
    protected abstract void imKickMember();


    /**
     * 加入黑名单
     * @param blackUserId
     */
    protected abstract void imAddBlackList(String blackUserId);

    /**
     * 从黑名单移除
     * @param blackUserId
     */
    protected abstract void imRemoveFromBlackList(String blackUserId);
}
