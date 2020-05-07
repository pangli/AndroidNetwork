package com.zorro.networking.internal;

import com.zorro.networking.common.ANConstants;
import com.zorro.networking.common.ANRequest;
import com.zorro.networking.common.ANResponse;
import com.zorro.networking.common.ResponseType;
import com.zorro.networking.error.ANError;
import com.zorro.networking.utils.SourceCloseUtil;
import com.zorro.networking.utils.Utils;

import okhttp3.Response;

import static com.zorro.networking.common.RequestType.DOWNLOAD;
import static com.zorro.networking.common.RequestType.MULTIPART;
import static com.zorro.networking.common.RequestType.SIMPLE;

/**
 * Package:   com.zorro.networking.internal
 * ClassName: SynchronousCall
 * Created by Zorro on 2020/5/6 19:50
 * 备注： 同步请求
 */
@SuppressWarnings("unchecked")
public final class SynchronousCall {

    private SynchronousCall() {

    }

    public static <T> ANResponse<T> execute(ANRequest request) {
        switch (request.getRequestType()) {
            case SIMPLE:
                return executeSimpleRequest(request);
            case DOWNLOAD:
                return executeDownloadRequest(request);
            case MULTIPART:
                return executeUploadRequest(request);
        }
        return new ANResponse<>(new ANError());
    }

    private static <T> ANResponse<T> executeSimpleRequest(ANRequest request) {
        Response okHttpResponse = null;
        try {
            okHttpResponse = InternalNetworking.performSimpleRequest(request);
            if (okHttpResponse == null) {
                return new ANResponse<>(Utils.getErrorForConnection(new ANError()));
            }

            if (request.getResponseAs() == ResponseType.OK_HTTP_RESPONSE) {
                ANResponse response = new ANResponse(okHttpResponse);
                response.setOkHttpResponse(okHttpResponse);
                return response;
            }
            if (okHttpResponse.code() >= 400) {
                ANResponse response = new ANResponse<>(Utils.getErrorForServerResponse(new ANError(okHttpResponse),
                        request, okHttpResponse.code()));
                response.setOkHttpResponse(okHttpResponse);
                return response;
            }
            ANResponse response = request.parseResponse(okHttpResponse);
            response.setOkHttpResponse(okHttpResponse);
            return response;
        } catch (ANError se) {
            return new ANResponse<>(Utils.getErrorForConnection(new ANError(se)));
        } catch (Exception e) {
            return new ANResponse<>(Utils.getErrorForConnection(new ANError(e)));
        } finally {
            SourceCloseUtil.close(okHttpResponse, request);
        }
    }

    private static <T> ANResponse<T> executeDownloadRequest(ANRequest request) {
        Response okHttpResponse;
        try {
            okHttpResponse = InternalNetworking.performDownloadRequest(request);
            if (okHttpResponse == null) {
                return new ANResponse<>(Utils.getErrorForConnection(new ANError()));
            }
            if (okHttpResponse.code() >= 400) {
                ANResponse response = new ANResponse<>(Utils.getErrorForServerResponse(new ANError(okHttpResponse),
                        request, okHttpResponse.code()));
                response.setOkHttpResponse(okHttpResponse);
                return response;
            }
            ANResponse response = new ANResponse(ANConstants.SUCCESS);
            response.setOkHttpResponse(okHttpResponse);
            return response;
        } catch (ANError se) {
            return new ANResponse<>(Utils.getErrorForConnection(new ANError(se)));
        } catch (Exception e) {
            return new ANResponse<>(Utils.getErrorForConnection(new ANError(e)));
        }
    }

    private static <T> ANResponse<T> executeUploadRequest(ANRequest request) {
        Response okHttpResponse = null;
        try {
            okHttpResponse = InternalNetworking.performUploadRequest(request);

            if (okHttpResponse == null) {
                return new ANResponse<>(Utils.getErrorForConnection(new ANError()));
            }

            if (request.getResponseAs() == ResponseType.OK_HTTP_RESPONSE) {
                ANResponse response = new ANResponse(okHttpResponse);
                response.setOkHttpResponse(okHttpResponse);
                return response;
            }
            if (okHttpResponse.code() >= 400) {
                ANResponse response = new ANResponse<>(Utils.getErrorForServerResponse(new ANError(okHttpResponse),
                        request, okHttpResponse.code()));
                response.setOkHttpResponse(okHttpResponse);
                return response;
            }
            ANResponse response = request.parseResponse(okHttpResponse);
            response.setOkHttpResponse(okHttpResponse);
            return response;
        } catch (ANError se) {
            return new ANResponse<>(Utils.getErrorForConnection(se));
        } catch (Exception e) {
            return new ANResponse<>(Utils.getErrorForConnection(new ANError(e)));
        } finally {
            SourceCloseUtil.close(okHttpResponse, request);
        }
    }
}
