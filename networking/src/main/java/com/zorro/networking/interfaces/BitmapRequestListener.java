package com.zorro.networking.interfaces;

import android.graphics.Bitmap;

import com.zorro.networking.error.ANError;

/**
 * Package:   com.zorro.networking.interfaces
 * ClassName: BitmapRequestListener
 * Created by Zorro on 2020/5/6 19:35
 * 备注：图片加载
 */
public interface BitmapRequestListener {

    void onResponse(Bitmap response);

    void onError(ANError anError);

}
