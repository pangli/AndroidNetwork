package com.zorro.networking.interfaces;

import com.zorro.networking.error.ANError;

import okhttp3.Response;

/**
 * Package:   com.zorro.networking.interfaces
 * ClassName: OkHttpResponseListener
 * Created by Zorro on 2020/5/6 19:44
 * 备注：
 */
public interface OkHttpResponseListener {

    void onResponse(Response response);

    void onError(ANError anError);

}
