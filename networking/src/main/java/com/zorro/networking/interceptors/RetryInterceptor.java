package com.zorro.networking.interceptors;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InterruptedIOException;

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
    private int maxRetry;//最大重试次数,假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）
    private long retryInterval;//重试的间隔

    public RetryInterceptor(int maxRetry, long retryInterval) {
        this.maxRetry = maxRetry;
        this.retryInterval = retryInterval;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        int retryNum = 0;
        while (!response.isSuccessful() && retryNum <= maxRetry) {
            System.out.println("retryNum=" + retryNum);
            try {
                Thread.sleep(retryInterval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new InterruptedIOException();
            }
            retryNum++;
            System.out.println("retryNum=" + retryNum);
            response = chain.proceed(request);
        }
        return response;
    }
}
