package com.videostar.bridge.scope;

import com.videostar.api.scope.ScopeCache;
import com.videostar.api.scope.ScopeDTO;

import com.videostar.ext.cache.Cache;
import com.videostar.ext.cache.CacheStrategy;

public class ScopeCacheImpl implements ScopeCache {
    private CacheStrategy cacheStrategy;
    private Cache cache;

    public ScopeDTO findById(String id) {
        String key = "scopeInfoId:" + id;

        return cache.get(key);
    }

    public ScopeDTO findByRef(String ref) {
        String key = "scopeInfoRef:" + ref;

        return cache.get(key);
    }

    public ScopeDTO findByCode(String code) {
        String key = "scopeInfoCode:" + code;

        return cache.get(key);
    }

    public void updateScope(ScopeDTO scopeDto) {
        cache.set("scopeInfoId:" + scopeDto.getId(), scopeDto);
        cache.set("scopeInfoRef:" + scopeDto.getId(), scopeDto);
        cache.set("scopeInfoCode:" + scopeDto.getId(), scopeDto);
    }

    public void removeScope(ScopeDTO scopeDto) {
        cache.remove("scopeInfoId:" + scopeDto.getId());
        cache.remove("scopeInfoRef:" + scopeDto.getId());
        cache.remove("scopeInfoCode:" + scopeDto.getId());
    }

    public void setCacheStrategy(CacheStrategy cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
        this.cache = cacheStrategy.getCache("scope");
    }
}
