package com.zorro.networking.internal;

import com.zorro.networking.common.ANRequest;
import com.zorro.networking.common.Priority;
import com.zorro.networking.core.Core;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Package:   com.zorro.networking.internal
 * ClassName: ANRequestQueue
 * Created by Zorro on 2020/5/6 19:45
 * 备注：请求队列
 */
public class ANRequestQueue {

    private final Set<ANRequest> mCurrentRequests =
            Collections.newSetFromMap(new ConcurrentHashMap<ANRequest, Boolean>());
    private AtomicInteger mSequenceGenerator = new AtomicInteger();
    private static ANRequestQueue sInstance = null;

    public static void initialize() {
        getInstance();
    }

    public static ANRequestQueue getInstance() {
        if (sInstance == null) {
            synchronized (ANRequestQueue.class) {
                if (sInstance == null) {
                    sInstance = new ANRequestQueue();
                }
            }
        }
        return sInstance;
    }

    public interface RequestFilter {
        boolean apply(ANRequest request);
    }

    private void cancel(RequestFilter filter, boolean forceCancel) {
        try {
            for (Iterator<ANRequest> iterator = mCurrentRequests.iterator(); iterator.hasNext(); ) {
                ANRequest request = iterator.next();
                if (filter.apply(request)) {
                    request.cancel(forceCancel);
                    if (request.isCanceled()) {
                        request.destroy();
                        iterator.remove();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelAll(boolean forceCancel) {
        try {
            for (Iterator<ANRequest> iterator = mCurrentRequests.iterator(); iterator.hasNext(); ) {
                ANRequest request = iterator.next();
                request.cancel(forceCancel);
                if (request.isCanceled()) {
                    request.destroy();
                    iterator.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelRequestWithGivenTag(final Object tag, final boolean forceCancel) {
        try {
            if (tag == null) {
                return;
            }
            cancel(new RequestFilter() {
                @Override
                public boolean apply(ANRequest request) {
                    return isRequestWithTheGivenTag(request, tag);
                }
            }, forceCancel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getSequenceNumber() {
        return mSequenceGenerator.incrementAndGet();
    }

    public ANRequest addRequest(ANRequest request) {
        try {
            mCurrentRequests.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            request.setSequenceNumber(getSequenceNumber());
            if (request.getPriority() == Priority.IMMEDIATE) {
                request.setFuture(Core.getInstance()
                        .getExecutorSupplier()
                        .forImmediateNetworkTasks()
                        .submit(new InternalRunnable(request)));
            } else {
                request.setFuture(Core.getInstance()
                        .getExecutorSupplier()
                        .forNetworkTasks()
                        .submit(new InternalRunnable(request)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return request;
    }

    public void finish(ANRequest request) {
        try {
            mCurrentRequests.remove(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isRequestRunning(Object tag) {
        try {
            for (ANRequest request : mCurrentRequests) {
                if (isRequestWithTheGivenTag(request, tag) && request.isRunning()) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isRequestWithTheGivenTag(ANRequest request, Object tag) {
        if (request.getTag() == null) {
            return false;
        }
        if (request.getTag() instanceof String && tag instanceof String) {
            final String tempRequestTag = (String) request.getTag();
            final String tempTag = (String) tag;
            return tempRequestTag.equals(tempTag);
        }
        return request.getTag().equals(tag);
    }

}
