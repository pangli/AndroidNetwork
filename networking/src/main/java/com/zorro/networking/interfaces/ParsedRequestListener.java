package com.zorro.networking.interfaces;

import com.zorro.networking.error.ANError;

/**
 * Package:   com.zorro.networking.interfaces
 * ClassName: ParsedRequestListener
 * Created by Zorro on 2020/5/6 19:43
 * 备注：
 */
public interface ParsedRequestListener<T> {

    void onResponse(T response);

    void onError(ANError anError);

}