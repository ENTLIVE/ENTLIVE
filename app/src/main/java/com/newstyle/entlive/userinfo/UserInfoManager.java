package com.newstyle.entlive.userinfo;

/**
 * Created by wangdong on 2018/3/16.
 *
 * 用不本地用户数据的存储和管理，可以对方法进行修改和添加
 */

public class UserInfoManager {

    private UserInfo mUserInfo;

    private UserInfoManager(){
        queryUserInfo();
    }

    private static class UserInfoMgrHolder{
        private static UserInfoManager instance = new UserInfoManager();
    }

    public static UserInfoManager getInstance(){
        return UserInfoMgrHolder.instance;
    }

    //获取本地存储的userinfo数据
    private void queryUserInfo(){
        //mUserInfo =
    }

    /**
     * 保持用户数据
     * @param userInfo
     */
    public void saveUserInfo(UserInfo userInfo){

    }

    /**
     * 获取用户数据
     * @return
     */
    public UserInfo getUserInfo(){
        return mUserInfo;
    }


    /**
     * 获取用户ID
     * @return
     */
    public int getUserId(){
        if (mUserInfo == null) {
            return 0;
        }
        return mUserInfo.userId;
    }

    /**
     * 获取用户名字
     * @return
     */
    public String getNickName(){
        if (mUserInfo == null) {
            return null;
        }
        return mUserInfo.nickName;
    }

    /**
     * 获取用户性别
     * @return
     */
    public int getUserSex(){
        if (mUserInfo == null) {
            return 0;
        }
        return mUserInfo.sex;
    }

}
