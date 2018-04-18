package com.newstyle.entlive.login;

/**
 * Created by wangdong on 2018/3/16.
 */

public class LoginManager {

    private LoginManager(){
    }

    private static class LoginMgrHolder {
        private static LoginManager instance = new LoginManager();
    }

    public static LoginManager getInstance() {
        return LoginMgrHolder.instance;
    }

    public interface LoginCallback {

        /**
         * 登录成功
         */
        void onSuccess();

        /**
         * 登录失败
         * @param code 错误码
         * @param msg 错误信息
         */
        void onFailure(int code, String msg);

    }

    /**
     * 注册和登录
     * @param username
     * @param password
     * @param callback
     */
    public void registerAndLogin(String username, String password,LoginCallback callback){

    }

    /**
     * 通过用户名密码登录
     * @param username
     * @param password
     */
    public void pwdLogin(String username, String password,LoginCallback callback){
        if (checkTokenAble()) {

        }
    }

    /**
     * 其他客户端登录
     * @param platformType
     */
    public void otherAuthLogin(int platformType,LoginCallback callback){
        if (checkTokenAble()) {

        }
    }

    /**
     * IM登录
     * @param userSig
     */
    public void imLogin(String userSig){

    }

    /**
     * 检查是否登录
     * @return
     */
    public boolean checkIsLogin(){

        return false;
    }

    /**
     * 检查token是否有效
     * @return
     */
    public boolean checkTokenAble(){

        return false;
    }

}
