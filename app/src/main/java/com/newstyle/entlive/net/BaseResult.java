package com.newstyle.entlive.net;

import java.io.Serializable;

/**
 * Created by wangdong on 2018/4/10.
 */

public class BaseResult<T> implements Serializable {
    /**
     * 网络请求结果消息
     */
    public String msg;
    /**
     * 网络请求结果code
     */
    public String code;
    /**
     * 请求ID唯一
     */
    public String requestId;
    /**
     * 返回的结果
     */
    public T result;

}
