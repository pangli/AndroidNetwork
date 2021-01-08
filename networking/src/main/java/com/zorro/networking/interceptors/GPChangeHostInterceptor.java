package com.zorro.networking.interceptors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Package:   com.sino.gameplus.core.net
 * ClassName: GPRetryInterceptor
 * Created by Zorro on 2020/5/22 11:31.
 * 备注：请求失败切换host截器，切线机制
 */
public class GPChangeHostInterceptor implements Interceptor {
    private int retryNum = 0;
    private int bodyCode = 0;

    @Override
    public Response intercept(Chain chain) throws IOException {
        this.retryNum = 0;
        this.bodyCode = 0;
        Request request = chain.request();
        return retry(chain, request);
    }

    private Response retry(Chain chain, Request request) throws IOException {
        Response response;
        HttpUrl httpUrl = request.url();
        List<String> pathSegments = httpUrl.pathSegments();
        if (pathSegments != null && pathSegments.contains("payment")) {
            List<String> payLines = new ArrayList<>();
            payLines.add("192.168.0.1");
            payLines.add("192.168.0.12");
            int maxRetry = payLines.size();//最大重试次数,假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）
            response = doRequest(chain, request);
//            if (response != null) {
//                ResponseBody responseBody = response.body();
//                if (responseBody != null) {
//                    BufferedSource source = responseBody.source();
//                    source.request(Long.MAX_VALUE); // Buffer the entire body.
//                    Buffer buffer = source.buffer();
//                    Charset charset = Charset.forName("UTF-8");
//                    MediaType contentType = responseBody.contentType();
//                    if (contentType != null) {
//                        charset = contentType.charset(Charset.forName("UTF-8"));
//                    }
//                    String body = buffer.clone().readString(charset);
//                    if (!TextUtils.isEmpty(body)) {
//                        try {
//                            HttpResults httpResults = new Gson().fromJson(body, HttpResults.class);
//                            bodyCode = httpResults.code;
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
            //过滤无支付权限6003
//            while ((response == null || !response.isSuccessful()) && bodyCode != 6003 && retryNum < maxRetry) {
            while ((response == null || !response.isSuccessful()) && bodyCode != 6003 && retryNum < maxRetry) {
                HttpUrl newHttpUrl = httpUrl.newBuilder().host(payLines.get(retryNum)).build();
                Request newRequest = request.newBuilder().url(newHttpUrl).build();
                retryNum++;
                response = retry(chain, newRequest);
            }
        } else {
            response = doRequest(chain, request);
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
