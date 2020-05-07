package com.zorro.networking.interfaces;

import com.zorro.networking.error.ANError;

/**
 * Package:   com.zorro.networking.interfaces
 * ClassName: StringRequestListener
 * Created by Zorro on 2020/5/6 19:43
 * 备注：
 */
public interface StringRequestListener {

    void onResponse(String response);

    void onError(ANError anError);

}
