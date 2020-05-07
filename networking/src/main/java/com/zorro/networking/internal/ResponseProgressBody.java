package com.zorro.networking.internal;

import com.zorro.networking.common.ANConstants;
import com.zorro.networking.interfaces.DownloadProgressListener;
import com.zorro.networking.model.Progress;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Package:   com.zorro.networking.internal
 * ClassName: ResponseProgressBody
 * Created by Zorro on 2020/5/6 19:50
 * 备注：
 */
public class ResponseProgressBody extends ResponseBody {

    private final ResponseBody mResponseBody;
    private BufferedSource bufferedSource;
    private DownloadProgressHandler downloadProgressHandler;

    public ResponseProgressBody(ResponseBody responseBody, DownloadProgressListener downloadProgressListener) {
        this.mResponseBody = responseBody;
        if (downloadProgressListener != null) {
            this.downloadProgressHandler = new DownloadProgressHandler(downloadProgressListener);
        }
    }

    @Override
    public MediaType contentType() {
        return mResponseBody.contentType();
    }

    @Override
    public long contentLength() {
        return mResponseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(mResponseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {

        return new ForwardingSource(source) {

            long totalBytesRead;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                totalBytesRead += ((bytesRead != -1) ? bytesRead : 0);
                if (downloadProgressHandler != null) {
                    downloadProgressHandler
                            .obtainMessage(ANConstants.UPDATE,
                                    new Progress(totalBytesRead, mResponseBody.contentLength()))
                            .sendToTarget();
                }
                return bytesRead;
            }
        };
    }
}
