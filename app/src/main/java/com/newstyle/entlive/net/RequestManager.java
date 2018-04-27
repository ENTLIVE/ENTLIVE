package com.newstyle.entlive.net;

import com.newstyle.entlive.userinfo.UserInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wangdong on 2018/4/10.
 */

public interface RequestManager {

    /**
     * 登录接口
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(NetConfig.Login.URL_LOGIN)
    Observable<BaseResult<UserInfo>> loginGetUserInfo(@FieldMap() Map<String, String> params);
}
