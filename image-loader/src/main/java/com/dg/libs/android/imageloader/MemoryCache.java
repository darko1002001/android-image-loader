package com.dg.libs.android.imageloader;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import android.graphics.Bitmap;

/**
 * @author Darko.Grozdanovski
 * @author Jan.Marincek
 **/
class MemoryCache {


    private static int HARD_CACHE_CAPACITY = 5;
    // Hard cache, with a fixed maximum capacity and a life duration
    private final HashMap<String, Bitmap> sHardBitmapCache;

    /**
     * Set the size of the hard cache, note that the soft cache will still be filled with displayed
     * photos
     * 
     * @param hardCacheCapacity
     */
    public MemoryCache(int hardCacheCapacity) {
        super();
        HARD_CACHE_CAPACITY = hardCacheCapacity;
        sHardBitmapCache = new LinkedHashMapExtension(HARD_CACHE_CAPACITY / 2, 0.75f, true);
        softReference = new ConcurrentHashMap<String, SoftReference<Bitmap>>(HARD_CACHE_CAPACITY / 2);
    }

    private final ConcurrentHashMap<String, SoftReference<Bitmap>> softReference;

    public Bitmap get(String id) {
        // First try the hard reference cache
        synchronized (sHardBitmapCache) {
            final Bitmap bitmap = sHardBitmapCache.get(id);
            if (bitmap != null) {
                // Bitmap found in hard cache
                // Move element to first position, so that it is removed last
                sHardBitmapCache.remove(id);
                sHardBitmapCache.put(id, bitmap);
                return bitmap;
            }
        }

        // Then try the soft reference cache
        SoftReference<Bitmap> bitmapReference = softReference.get(id);
        if (bitmapReference != null) {
            final Bitmap bitmap = bitmapReference.get();
            if (bitmap != null) {
                // Bitmap found in soft cache
                return bitmap;
            }
        }
        // Soft reference has been Garbage Collected
        softReference.remove(id);
        return null;
    }

    public void put(String id, Bitmap bitmap) {
        if (bitmap != null) {
            synchronized (sHardBitmapCache) {
                sHardBitmapCache.put(id, bitmap);
            }
        }
    }

    public String getObjectCounters() {
        return "Hard Cache: " + sHardBitmapCache.size() + " Soft Reference" + softReference.size();
    }

    public void clear() {
        softReference.clear();
        sHardBitmapCache.clear();
    }

    private final class LinkedHashMapExtension extends LinkedHashMap<String, Bitmap> {

        private static final long serialVersionUID = -3956509122620786256L;

        private LinkedHashMapExtension(int initialCapacity, float loadFactor, boolean accessOrder) {
            super(initialCapacity, loadFactor, accessOrder);
        }

        @Override
        protected boolean removeEldestEntry(Entry<String, Bitmap> eldest) {
            if (size() > HARD_CACHE_CAPACITY) {
                // Entries push-out of hard reference cache are transferred to
                // soft reference cache
                softReference.put(eldest.getKey(), new SoftReference<Bitmap>(eldest.getValue()));
                return true;
            } else {
                return false;
            }
        }
    }

}
