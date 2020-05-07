package com.zorro.networking.core;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * Package:   com.zorro.networking.core
 * ClassName: MainThreadExecutor
 * Created by Zorro on 2020/5/6 19:26
 * 备注： 主线程执行器
 */
public class MainThreadExecutor implements Executor {

    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(Runnable runnable) {
        handler.post(runnable);
    }
}
