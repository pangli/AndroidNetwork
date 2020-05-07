package com.zorro.networking.interfaces;

import com.zorro.networking.error.ANError;

import org.json.JSONArray;

import okhttp3.Response;

/**
 * Package:   com.zorro.networking.interfaces
 * ClassName: OkHttpResponseAndJSONArrayRequestListener
 * Created by Zorro on 2020/5/6 19:44
 * 备注：
 */
public interface OkHttpResponseAndJSONArrayRequestListener {

    void onResponse(Response okHttpResponse, JSONArray response);

    void onError(ANError anError);

}
