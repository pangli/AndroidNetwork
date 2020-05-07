package com.zorro.networking.utils;

import com.zorro.networking.common.ANRequest;
import com.zorro.networking.common.ResponseType;

import okhttp3.Response;

/**
 * Package:   com.zorro.networking.utils
 * ClassName: SourceCloseUtil
 * Created by Zorro on 2020/5/6 19:53
 * 备注：
 */
public final class SourceCloseUtil {

    private SourceCloseUtil() {
    }

    public static void close(Response response, ANRequest request) {
        if (request.getResponseAs() != ResponseType.OK_HTTP_RESPONSE &&
                response != null && response.body() != null &&
                response.body().source() != null) {
            try {
                response.body().source().close();
            } catch (Exception ignore) {

            }
        }
    }
}
