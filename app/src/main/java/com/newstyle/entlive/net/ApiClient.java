package com.newstyle.entlive.net;

import com.newstyle.entlive.userinfo.UserInfo;
import com.newstyle.entlive.util.LogUtil;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by wangdong on 2018/4/9.
 */

public class ApiClient {

    private static final int HTTP_CONNECTION_TIMEOUT = 8 * 1000;

    private OkHttpClient mOkHttpClient;
    private RequestManager mRequestManager;
    private static ApiClient mInstance; // 单例


    private ApiClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (LogUtil.isDebugMode()) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        Interceptor netInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                okhttp3.Request originalRequest = chain.request();
                RequestCommentSetting requestCommentSetting = new RequestCommentSetting(originalRequest);
                Request authorised = requestCommentSetting.getAuthorised();
                requestCommentSetting.showHeaderLog();

                return chain.proceed(authorised);
            }
        };

        mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(HTTP_CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(HTTP_CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(HTTP_CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .addNetworkInterceptor(netInterceptor)
                .retryOnConnectionFailure(true).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(NetConfig.SERVER_URL)
                //.addConverterFactory(BaseResultConverterFactory.create())//用于过滤所有接口返回token失效code时，统一动作
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mRequestManager = retrofit.create(RequestManager.class);

    }

    /**
     * 获取API请求实例
     *
     * @return 单例对象
     */
    public static ApiClient getInstance() {
        if (mInstance == null) {
            synchronized (ApiClient.class) {
                if (mInstance == null) {
                    mInstance = new ApiClient();
                }
            }
        }
        return mInstance;
    }


    /**
     * 登录接口
     * @param params
     * @return
     */
    public static Observable<BaseResult<UserInfo>> loginGetUserInfo(Map<String, String> params){
        return ApiClient.getInstance().mRequestManager.loginGetUserInfo(params);
    }

}
