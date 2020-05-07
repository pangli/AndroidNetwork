package com.zorro.networking.model;

import java.io.Serializable;

/**
 * Package:   com.zorro.networking.model
 * ClassName: Progress
 * Created by Zorro on 2020/5/6 19:51
 * 备注：
 */
public class Progress implements Serializable {

    public long currentBytes;
    public long totalBytes;

    public Progress(long currentBytes, long totalBytes) {
        this.currentBytes = currentBytes;
        this.totalBytes = totalBytes;
    }

}