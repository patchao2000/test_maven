package com.videostar.bridge.userrepo;

import com.videostar.api.userrepo.UserRepoCache;
import com.videostar.api.userrepo.UserRepoDTO;

import com.videostar.ext.cache.Cache;
import com.videostar.ext.cache.CacheStrategy;

public class UserRepoCacheImpl implements UserRepoCache {
    private CacheStrategy cacheStrategy;
    private Cache cache;

    public UserRepoDTO findById(String id) {
        String key = "userRepoId:" + id;

        return cache.get(key);
    }

    public UserRepoDTO findByCode(String code) {
        String key = "userRepoCode:" + code;

        return cache.get(key);
    }

    public void updateUserRepo(UserRepoDTO userRepoDto) {
        cache.set("userRepoId:" + userRepoDto.getId(), userRepoDto);
        cache.set("userRepoCode:" + userRepoDto.getCode(), userRepoDto);
    }

    public void removeUserRepo(UserRepoDTO userRepoDto) {
        cache.remove("userRepoId:" + userRepoDto.getId());
        cache.remove("userRepoCode:" + userRepoDto.getCode());
    }

    public void setCacheStrategy(CacheStrategy cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
        this.cache = cacheStrategy.getCache("userrepo");
    }
}
