package com.zorro.networking.core;

import java.util.concurrent.Executor;

/**
 * Package:   com.zorro.networking.core
 * ClassName: ExecutorSupplier
 * Created by Zorro on 2020/5/6 19:25
 * 备注： 执行器接口
 */
public interface ExecutorSupplier {

    ANExecutor forNetworkTasks();

    ANExecutor forImmediateNetworkTasks();

    Executor forMainThreadTasks();
}
