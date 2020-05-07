package com.zorro.networking.interfaces;

/**
 * Package:   com.zorro.networking.interfaces
 * ClassName: DownloadProgressListener
 * Created by Zorro on 2020/5/6 19:37
 * 备注：下载进度监听
 */
public interface DownloadProgressListener {
    void onProgress(long bytesDownloaded, long totalBytes);
}
