package com.zorro.networking.model;

import java.io.File;
/**
 * Package:   com.zorro.networking.model
 * ClassName: MultipartFileBody
 * Created by Zorro on 2020/5/6 19:51
 * 备注：
 */
public class MultipartFileBody {

    public final File file;
    public final String contentType;

    public MultipartFileBody(File file, String contentType) {
        this.file = file;
        this.contentType = contentType;
    }

}
