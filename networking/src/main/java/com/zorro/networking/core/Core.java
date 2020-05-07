package com.zorro.networking.core;

/**
 * Package:   com.zorro.networking.core
 * ClassName: Core
 * Created by Zorro on 2020/5/6 19:24
 * 备注： 执行器
 */
public class Core {

    private static Core sInstance = null;
    private final ExecutorSupplier mExecutorSupplier;

    private Core() {
        this.mExecutorSupplier = new DefaultExecutorSupplier();
    }

    public static Core getInstance() {
        if (sInstance == null) {
            synchronized (Core.class) {
                if (sInstance == null) {
                    sInstance = new Core();
                }
            }
        }
        return sInstance;
    }

    public ExecutorSupplier getExecutorSupplier() {
        return mExecutorSupplier;
    }

    public static void shutDown() {
        if (sInstance != null) {
            sInstance = null;
        }
    }
}
