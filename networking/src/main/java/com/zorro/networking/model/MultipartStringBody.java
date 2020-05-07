package com.zorro.networking.model;
/**
 * Package:   com.zorro.networking.model
 * ClassName: MultipartStringBody
 * Created by Zorro on 2020/5/6 19:51
 * 备注：
 */
public class MultipartStringBody {

    public final String value;
    public final String contentType;

    public MultipartStringBody(String value, String contentType) {
        this.value = value;
        this.contentType = contentType;
    }

}
