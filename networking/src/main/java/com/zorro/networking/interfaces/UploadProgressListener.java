package com.zorro.networking.interfaces;

/**
 * Package:   com.zorro.networking.interfaces
 * ClassName: UploadProgressListener
 * Created by Zorro on 2020/5/6 19:43
 * 备注：上传进度监听器
 */
public interface UploadProgressListener {
    void onProgress(long bytesUploaded, long totalBytes);
}
