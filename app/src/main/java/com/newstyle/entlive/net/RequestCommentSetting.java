package com.newstyle.entlive.net;

import android.text.TextUtils;

import com.newstyle.entlive.Appli;
import com.newstyle.entlive.userinfo.UserInfo;
import com.newstyle.entlive.userinfo.UserInfoManager;
import com.newstyle.entlive.util.Utils;

import java.util.UUID;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by wangdong on 2018/4/10.
 */

public class RequestCommentSetting {
    private Request mAuthorised;

    public RequestCommentSetting(Request originalRequest){
        HttpUrl hadCommentSettingUrl = getCommentHttpUrl(originalRequest);
        UserInfo userInfo = UserInfoManager.getInstance().getUserInfo();
        /*LocationEntity locationEntity = OtherDataCacheUtils.getLocationInfo(Appli.getContext());
        String lat = "0";
        String lon = "0";
        if (locationEntity != null) {
            lat = locationEntity.latitude == null ? lat : locationEntity.latitude;
            lon = locationEntity.longitude == null ? lon : locationEntity.longitude;
        }*/

        int uid = userInfo == null ? 0 : userInfo.userId;
        String token = userInfo.token;
        /*mAuthorised = originalRequest.newBuilder()
                .addHeader(HEADER_DID, Utils.getDeviceId(Appli.getContext()))
                .addHeader(HEADER_CHANNEL, ChannelUtil.getChannel(Appli.getContext()))
                .addHeader(HEADER_OS, "2")
                .addHeader(HEADER_OSV, TextUtils.isEmpty(android.os.Build.VERSION.RELEASE) ? "" : android.os.Build.VERSION.RELEASE)
                .addHeader(HEADER_VER, Utils.getVersionName(Appli.getContext()))
                .addHeader(HEADER_DEVICEID, SmAntiFraud.getDeviceId())
                .addHeader(HEADER_LAT, getLat())
                .addHeader(HEADER_LON, getLon())
                .addHeader(HEADER_NET, String.valueOf(SettingPreference.getNetType(Appli.getContext())))
                .addHeader(HEADER_NETAGE, String.valueOf(SettingPreference.getNetAgeType(Appli.getContext())))
                .addHeader(HEADER_UID, TextUtils.isEmpty(getUid()) ? "" : getUid())
                .addHeader(HEADER_LID, TextUtils.isEmpty(getToken()) ? "" : getToken())
                .method(mOriginalRequest.method(), mOriginalRequest.body())
                .url(mHadCommentSettingUrl)
                .build();*/
    }

    public Request getAuthorised() {
        return mAuthorised;
    }

    public void showHeaderLog() {
        /*Utils.logLocal("OkHttp-header: ", NetConfig.Login.uid + ":" + getUid() + "\n" +
                NetConfig.Login.access_token + ":" + getToken() + "\n" +
                NetConfig.Login.deviceId + ":" + Utils.getDeviceId(Appli.getContext()) + "\n" +
                HEADER_DID + ":" + Utils.getDeviceId(Appli.getContext()) + "_" + Utils.getAndroidId(Appli.getContext()) + "\n" +
                HEADER_CHANNEL + ":" + ChannelUtil.getChannel(Appli.getContext()) + "\n" +
                HEADER_OSV + ":" + android.os.Build.VERSION.RELEASE + "\n" +
                HEADER_VER + ":" + Utils.getVersionName(Appli.getContext()) + "\n" +
                HEADER_DEVICEID + ":" + Utils.getDeviceId(Appli.getContext()) + "\n" +
                HEADER_LAT + ":" + getLat() + "\n" +
                HEADER_LON + ":" + getLon() + "\n" +
                HEADER_NET + ":" + String.valueOf(SettingPreference.getNetType(Appli.getContext())) + "\n" +
                HEADER_NETAGE + ":" + String.valueOf(SettingPreference.getNetAgeType(Appli.getContext())) + "\n" +
                HEADER_UID + ":" + getUid() + "\n" +
                HEADER_LID + ":" + getToken() + "\n");*/
    }

    public HttpUrl getCommentHttpUrl(Request originalRequest) {
        HttpUrl.Builder builder = originalRequest.url().newBuilder()
                .scheme(originalRequest.url().scheme())
                .host(originalRequest.url().host())
                .addQueryParameter("requestId", UUID.randomUUID().toString())
                .addQueryParameter("v", "");
        //checkAndAddTParameter(originalRequest, builder);
        return builder.build();
    }

}
