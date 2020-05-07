package com.zorro.networking.interfaces;

import com.zorro.networking.common.ConnectionQuality;

/**
 * Package:   com.zorro.networking.interfaces
 * ClassName: ConnectionQualityChangeListener
 * Created by Zorro on 2020/5/6 19:36
 * 备注：网络质量更改侦听器
 */
public interface ConnectionQualityChangeListener {

    void onChange(ConnectionQuality currentConnectionQuality, int currentBandwidth);
}
