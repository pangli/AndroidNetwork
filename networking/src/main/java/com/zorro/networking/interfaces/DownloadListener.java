package com.zorro.networking.interfaces;

import com.zorro.networking.error.ANError;

/**
 * Package:   com.zorro.networking.interfaces
 * ClassName: DownloadListener
 * Created by Zorro on 2020/5/6 19:37
 * 备注： 下载监听
 */
public interface DownloadListener {

    void onDownloadComplete();

    void onError(ANError anError);
}
