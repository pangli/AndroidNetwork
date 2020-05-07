package com.zorro.networking.core;

import android.os.Process;

import java.util.concurrent.ThreadFactory;

/**
 * Package:   com.zorro.networking.core
 * ClassName: PriorityThreadFactory
 * Created by Zorro on 2020/5/6 19:26
 * 备注：线程优先级处理
 */
public class PriorityThreadFactory implements ThreadFactory {

    private final int mThreadPriority;

    public PriorityThreadFactory(int threadPriority) {
        mThreadPriority = threadPriority;
    }

    @Override
    public Thread newThread(final Runnable runnable) {
        Runnable wrapperRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Process.setThreadPriority(mThreadPriority);
                } catch (Throwable t) {

                }
                runnable.run();
            }
        };
        return new Thread(wrapperRunnable);
    }

}
