package com.zorro.networking.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.zorro.networking.common.ANConstants;
import com.zorro.networking.interfaces.DownloadProgressListener;
import com.zorro.networking.model.Progress;

/**
 * Package:   com.zorro.networking.internal
 * ClassName: DownloadProgressHandler
 * Created by Zorro on 2020/5/6 19:46
 * 备注：下载进度Handler
 */
public class DownloadProgressHandler extends Handler {

    private final DownloadProgressListener mDownloadProgressListener;

    public DownloadProgressHandler(DownloadProgressListener downloadProgressListener) {
        super(Looper.getMainLooper());
        mDownloadProgressListener = downloadProgressListener;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case ANConstants.UPDATE:
                if (mDownloadProgressListener != null) {
                    final Progress progress = (Progress) msg.obj;
                    mDownloadProgressListener.onProgress(progress.currentBytes, progress.totalBytes);
                }
                break;
            default:
                super.handleMessage(msg);
                break;
        }
    }
}
