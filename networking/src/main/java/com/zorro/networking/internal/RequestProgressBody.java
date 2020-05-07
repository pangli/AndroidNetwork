package com.zorro.networking.internal;

import com.zorro.networking.common.ANConstants;
import com.zorro.networking.interfaces.UploadProgressListener;
import com.zorro.networking.model.Progress;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Package:   com.zorro.networking.internal
 * ClassName: RequestProgressBody
 * Created by Zorro on 2020/5/6 19:49
 * 备注：
 */
public class RequestProgressBody extends RequestBody {
    private final RequestBody requestBody;
    private BufferedSink bufferedSink;
    private UploadProgressHandler uploadProgressHandler;

    public RequestProgressBody(RequestBody requestBody, UploadProgressListener uploadProgressListener) {
        this.requestBody = requestBody;
        if (uploadProgressListener != null) {
            this.uploadProgressHandler = new UploadProgressHandler(uploadProgressListener);
        }
    }

    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return requestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if (bufferedSink == null) {
            bufferedSink = Okio.buffer(sink(sink));
        }
        requestBody.writeTo(bufferedSink);
        bufferedSink.flush();
    }

    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {
            long bytesWritten = 0L;
            long contentLength = 0L;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (contentLength == 0) {
                    contentLength = contentLength();
                }
                bytesWritten += byteCount;
                if (uploadProgressHandler != null) {
                    uploadProgressHandler.obtainMessage(ANConstants.UPDATE,
                            new Progress(bytesWritten, contentLength)).sendToTarget();
                }
            }
        };
    }
}