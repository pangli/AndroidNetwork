package com.zorro.networking.interceptors;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Package:   com.zorro.networking.interceptors
 * ClassName: RetryInterceptor
 * Created by Zorro on 2020/5/22 11:31.
 * 备注：重试拦截器，重试机制
 */
public class RetryInterceptor implements Interceptor {
    private int retryNum = 0;
    private int maxRetry;//最大重试次数,假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）
    private long retryInterval;//重试的间隔

    public RetryInterceptor(int maxRetry, long retryInterval) {
        this.retryNum = 0;
        this.maxRetry = maxRetry;
        this.retryInterval = retryInterval;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        this.retryNum = 0;
        return retry(chain);
    }

    private Response retry(Chain chain) {
        Request request = chain.request();
        Response response = doRequest(chain, request);
        while ((response == null || !response.isSuccessful()) && retryNum < maxRetry) {
            try {
                Thread.sleep(retryInterval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            retryNum++;
            response = retry(chain);
        }
        return response;
    }


    private Response doRequest(Chain chain, Request request) {
        Response response = null;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
