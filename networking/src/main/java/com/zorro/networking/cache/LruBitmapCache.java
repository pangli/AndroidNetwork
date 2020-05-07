package com.zorro.networking.cache;

import android.graphics.Bitmap;

import com.zorro.networking.internal.ANImageLoader;

/**
 * Package:   com.zorro.networking.cache
 * ClassName: LruBitmapCache
 * Created by Zorro on 2020/5/6 19:02
 * 备注：
 */
public class LruBitmapCache extends LruCache<String, Bitmap>
        implements ANImageLoader.ImageCache {

    public LruBitmapCache(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight();
    }

    @Override
    public Bitmap getBitmap(String key) {
        return get(key);
    }

    @Override
    public void evictBitmap(String key) {
        remove(key);
    }

    @Override
    public void evictAllBitmap() {
        evictAll();
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }

}
