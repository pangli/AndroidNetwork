package com.zorro.networking.interfaces;

import android.graphics.Bitmap;

import com.zorro.networking.error.ANError;

import okhttp3.Response;

/**
 * Package:   com.zorro.networking.interfaces
 * ClassName: OkHttpResponseAndBitmapRequestListener
 * Created by Zorro on 2020/5/6 19:42
 * 备注：
 */
public interface OkHttpResponseAndBitmapRequestListener {

    void onResponse(Response okHttpResponse, Bitmap response);

    void onError(ANError anError);

}
