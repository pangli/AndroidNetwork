package com.zorro.networking.interfaces;

import com.zorro.networking.error.ANError;

import org.json.JSONObject;

import okhttp3.Response;

/**
 * Package:   com.zorro.networking.interfaces
 * ClassName: OkHttpResponseAndJSONObjectRequestListener
 * Created by Zorro on 2020/5/6 19:42
 * 备注：
 */
public interface OkHttpResponseAndJSONObjectRequestListener {

    void onResponse(Response okHttpResponse, JSONObject response);

    void onError(ANError anError);

}
