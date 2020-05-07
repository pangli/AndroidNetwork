package com.zorro.networking.error;

import com.zorro.networking.common.ANConstants;
import com.zorro.networking.utils.ParseUtil;

import okhttp3.Response;

/**
 * Package:   com.zorro.networking.error
 * ClassName: ANError
 * Created by Zorro on 2020/5/6 19:27
 * 备注：请求异常
 */
@SuppressWarnings({"unchecked", "unused"})
public class ANError extends Exception {

    private String errorBody;

    private int errorCode = 0;

    private String errorDetail;

    private Response response;

    public ANError() {
    }

    public ANError(Response response) {
        this.response = response;
    }

    public ANError(String message) {
        super(message);
    }

    public ANError(String message, Response response) {
        super(message);
        this.response = response;
    }

    public ANError(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ANError(String message, Response response, Throwable throwable) {
        super(message, throwable);
        this.response = response;
    }

    public ANError(Response response, Throwable throwable) {
        super(throwable);
        this.response = response;
    }

    public ANError(Throwable throwable) {
        super(throwable);
    }

    public Response getResponse() {
        return response;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public String getErrorDetail() {
        return this.errorDetail;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setCancellationMessageInError() {
        this.errorDetail = ANConstants.REQUEST_CANCELLED_ERROR;
    }

    public String getErrorBody() {
        return errorBody;
    }

    public void setErrorBody(String errorBody) {
        this.errorBody = errorBody;
    }

    public <T> T getErrorAsObject(Class<T> objectClass) {
        try {
            return (T) (ParseUtil
                    .getParserFactory()
                    .getObject(errorBody, objectClass));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
