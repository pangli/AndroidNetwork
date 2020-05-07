package com.zorro.networking.interfaces;

import com.zorro.networking.error.ANError;

import okhttp3.Response;

/**
 * Package:   com.zorro.networking.interfaces
 * ClassName: OkHttpResponseAndStringRequestListener
 * Created by Zorro on 2020/5/6 19:42
 * 备注：
 */
public interface OkHttpResponseAndStringRequestListener {

    void onResponse(Response okHttpResponse, String response);

    void onError(ANError anError);

}
