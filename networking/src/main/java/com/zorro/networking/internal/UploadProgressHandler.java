package com.zorro.networking.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.zorro.networking.common.ANConstants;
import com.zorro.networking.interfaces.UploadProgressListener;
import com.zorro.networking.model.Progress;

/**
 * Package:   com.zorro.networking.internal
 * ClassName: UploadProgressHandler
 * Created by Zorro on 2020/5/6 19:51
 * 备注：
 */
public class UploadProgressHandler extends Handler {

    private final UploadProgressListener mUploadProgressListener;

    public UploadProgressHandler(UploadProgressListener uploadProgressListener) {
        super(Looper.getMainLooper());
        mUploadProgressListener = uploadProgressListener;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case ANConstants.UPDATE:
                if (mUploadProgressListener != null) {
                    final Progress progress = (Progress) msg.obj;
                    mUploadProgressListener.onProgress(progress.currentBytes, progress.totalBytes);
                }
                break;
            default:
                super.handleMessage(msg);
                break;
        }
    }
}
