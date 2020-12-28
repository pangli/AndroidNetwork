package com.zorro.networking.interceptors;

import com.zorro.networking.internal.InternalNetworking;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Package:   com.zorro.networking.interceptors
 * ClassName: DynamicConnectTimeout
 * Created by Zorro on 2020/12/28 10:27.
 * 备注：动态设置timeout 时间
 */
public class DynamicConnectTimeout implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        return chain.withConnectTimeout(InternalNetworking.sConnectTimeout, TimeUnit.MILLISECONDS)
                .withReadTimeout(InternalNetworking.sReadTimeout, TimeUnit.MILLISECONDS)
                .withWriteTimeout(InternalNetworking.sWriteTimeout, TimeUnit.MILLISECONDS).proceed(request);
    }
}
