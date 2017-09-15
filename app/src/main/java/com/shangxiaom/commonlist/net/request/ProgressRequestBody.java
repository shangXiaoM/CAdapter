package com.shangxiaom.commonlist.net.request;

import android.support.annotation.Nullable;

import com.shangxiaom.commonlist.net.OnRequestProgressLinstener;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.net.request
 * @ 日        期:2017/9/15 18:21
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public class ProgressRequestBody extends RequestBody {

    private RequestBody mRequestBody;
    private OnRequestProgressLinstener mProgressListener;
    private BufferedSink bufferedSink;

    public ProgressRequestBody(File file, OnRequestProgressLinstener progressListener) {
        this.mRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        this.mProgressListener = progressListener;
    }

    public ProgressRequestBody(RequestBody requestBody, OnRequestProgressLinstener progressListener) {
        this.mRequestBody = requestBody;
        this.mProgressListener = progressListener;
    }

    /**
     * Returns the Content-Type header for this body.
     */
    @Nullable
    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return mRequestBody.contentLength();
    }

    /**
     * Writes the content of this request to {@code out}.
     *
     * @param sink
     */
    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if (bufferedSink == null) {
            bufferedSink = Okio.buffer(sink(sink));
        }
        //写入
        mRequestBody.writeTo(bufferedSink);
        //必须调用flush，否则最后一部分数据可能不会被写入
        bufferedSink.flush();
    }

    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {
            //当前写入字节数
            long bytesWritten = 0L;
            //总字节长度，避免多次调用contentLength()方法
            long contentLength = 0L;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (contentLength == 0) {
                    //获得contentLength的值，后续不再调用
                    contentLength = contentLength();
                }
                //增加当前写入的字节数
                bytesWritten += byteCount;
                //回调上传接口
                mProgressListener.onProgress(bytesWritten, contentLength, bytesWritten == contentLength);
            }
        };
    }
}
