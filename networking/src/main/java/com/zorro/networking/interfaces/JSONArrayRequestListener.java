package com.zorro.networking.interfaces;

import com.zorro.networking.error.ANError;

import org.json.JSONArray;

/**
 * Package:   com.zorro.networking.interfaces
 * ClassName: JSONArrayRequestListener
 * Created by Zorro on 2020/5/6 19:38
 * 备注：返回JSONArrays数据监听
 */
public interface JSONArrayRequestListener {

    void onResponse(JSONArray response);

    void onError(ANError anError);

}
