package com.videostar.ext.cache;

import com.videostar.ext.cache.Cache;

public interface CacheStrategy {
    Cache getCache(String name);
}
