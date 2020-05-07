package com.zorro.networking.common;

import com.zorro.networking.core.Core;
import com.zorro.networking.interfaces.ConnectionQualityChangeListener;

/**
 * Package:   com.zorro.networking.common
 * ClassName: ConnectionClassManager
 * Created by Zorro on 2020/5/6 19:09
 * 备注：连接类管理器
 */
public class ConnectionClassManager {

    private static final int BYTES_TO_BITS = 8;
    private static final int DEFAULT_SAMPLES_TO_QUALITY_CHANGE = 5;
    private static final int MINIMUM_SAMPLES_TO_DECIDE_QUALITY = 2;
    private static final int DEFAULT_POOR_BANDWIDTH = 150;
    private static final int DEFAULT_MODERATE_BANDWIDTH = 550;
    private static final int DEFAULT_GOOD_BANDWIDTH = 2000;
    private static final long BANDWIDTH_LOWER_BOUND = 10;

    private static ConnectionClassManager sInstance;
    private ConnectionQuality mCurrentConnectionQuality = ConnectionQuality.UNKNOWN;
    private int mCurrentBandwidthForSampling = 0;
    private int mCurrentNumberOfSample = 0;
    private int mCurrentBandwidth = 0;
    private ConnectionQualityChangeListener mConnectionQualityChangeListener;

    public static ConnectionClassManager getInstance() {
        if (sInstance == null) {
            synchronized (ConnectionClassManager.class) {
                if (sInstance == null) {
                    sInstance = new ConnectionClassManager();
                }
            }
        }
        return sInstance;
    }

    public synchronized void updateBandwidth(long bytes, long timeInMs) {
        if (timeInMs == 0 || bytes < 20000 || (bytes) * 1.0 / (timeInMs) *
                BYTES_TO_BITS < BANDWIDTH_LOWER_BOUND) {
            return;
        }
        double bandwidth = (bytes) * 1.0 / (timeInMs) * BYTES_TO_BITS;
        mCurrentBandwidthForSampling = (int) ((mCurrentBandwidthForSampling *
                mCurrentNumberOfSample + bandwidth) / (mCurrentNumberOfSample + 1));
        mCurrentNumberOfSample++;
        if (mCurrentNumberOfSample == DEFAULT_SAMPLES_TO_QUALITY_CHANGE ||
                (mCurrentConnectionQuality == ConnectionQuality.UNKNOWN &&
                        mCurrentNumberOfSample == MINIMUM_SAMPLES_TO_DECIDE_QUALITY)) {
            final ConnectionQuality lastConnectionQuality = mCurrentConnectionQuality;
            mCurrentBandwidth = mCurrentBandwidthForSampling;
            if (mCurrentBandwidthForSampling <= 0) {
                mCurrentConnectionQuality = ConnectionQuality.UNKNOWN;
            } else if (mCurrentBandwidthForSampling < DEFAULT_POOR_BANDWIDTH) {
                mCurrentConnectionQuality = ConnectionQuality.POOR;
            } else if (mCurrentBandwidthForSampling < DEFAULT_MODERATE_BANDWIDTH) {
                mCurrentConnectionQuality = ConnectionQuality.MODERATE;
            } else if (mCurrentBandwidthForSampling < DEFAULT_GOOD_BANDWIDTH) {
                mCurrentConnectionQuality = ConnectionQuality.GOOD;
            } else if (mCurrentBandwidthForSampling > DEFAULT_GOOD_BANDWIDTH) {
                mCurrentConnectionQuality = ConnectionQuality.EXCELLENT;
            }
            if (mCurrentNumberOfSample == DEFAULT_SAMPLES_TO_QUALITY_CHANGE) {
                mCurrentBandwidthForSampling = 0;
                mCurrentNumberOfSample = 0;
            }
            if (mCurrentConnectionQuality != lastConnectionQuality &&
                    mConnectionQualityChangeListener != null) {
                Core.getInstance().getExecutorSupplier().forMainThreadTasks()
                        .execute(new Runnable() {
                            @Override
                            public void run() {
                                mConnectionQualityChangeListener
                                        .onChange(mCurrentConnectionQuality, mCurrentBandwidth);
                            }
                        });
            }
        }

    }

    public int getCurrentBandwidth() {
        return mCurrentBandwidth;
    }

    public ConnectionQuality getCurrentConnectionQuality() {
        return mCurrentConnectionQuality;
    }

    public void setListener(ConnectionQualityChangeListener connectionQualityChangeListener) {
        mConnectionQualityChangeListener = connectionQualityChangeListener;
    }

    public void removeListener() {
        mConnectionQualityChangeListener = null;
    }

    public static void shutDown() {
        if (sInstance != null) {
            sInstance = null;
        }
    }

}
