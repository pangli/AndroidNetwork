package com.zorro.networking.interfaces;

/**
 * Package:   com.zorro.networking.interfaces
 * ClassName: AnalyticsListener
 * Created by Zorro on 2020/5/6 19:33
 * 备注： 请求监控
 */
public interface AnalyticsListener {

    void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache);

}
