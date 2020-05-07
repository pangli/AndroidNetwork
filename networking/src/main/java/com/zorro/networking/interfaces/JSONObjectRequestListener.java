package com.zorro.networking.interfaces;

import com.zorro.networking.error.ANError;

import org.json.JSONObject;

/**
 * Package:   com.zorro.networking.interfaces
 * ClassName: JSONObjectRequestListener
 * Created by Zorro on 2020/5/6 19:39
 * 备注：返回JSONObject数据监听
 */
public interface JSONObjectRequestListener {

    void onResponse(JSONObject response);

    void onError(ANError anError);

}
