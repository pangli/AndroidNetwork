package com.zorro.networking.common;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Package:   com.zorro.networking.common
 * ClassName: RequestBuilder
 * Created by Zorro on 2020/5/6 19:21
 * 备注：请求构建
 */
public interface RequestBuilder {

    RequestBuilder setPriority(Priority priority);

    RequestBuilder setTag(Object tag);

    RequestBuilder addHeaders(String key, String value);

    RequestBuilder addHeaders(Map<String, String> headerMap);

    RequestBuilder addHeaders(Object object);

    RequestBuilder addQueryParameter(String key, String value);

    RequestBuilder addQueryParameter(Map<String, String> queryParameterMap);

    RequestBuilder addQueryParameter(Object object);

    RequestBuilder addPathParameter(String key, String value);

    RequestBuilder addPathParameter(Map<String, String> pathParameterMap);

    RequestBuilder addPathParameter(Object object);

    RequestBuilder doNotCacheResponse();

    RequestBuilder getResponseOnlyIfCached();

    RequestBuilder getResponseOnlyFromNetwork();

    RequestBuilder setMaxAgeCacheControl(int maxAge, TimeUnit timeUnit);

    RequestBuilder setMaxStaleCacheControl(int maxStale, TimeUnit timeUnit);

    RequestBuilder setExecutor(Executor executor);

    RequestBuilder setOkHttpClient(OkHttpClient okHttpClient);

    RequestBuilder setUserAgent(String userAgent);

}
