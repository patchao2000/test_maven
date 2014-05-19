package com.videostar.bridge.user;

import com.videostar.api.user.UserCache;
import com.videostar.api.user.UserDTO;

import com.videostar.ext.cache.Cache;
import com.videostar.ext.cache.CacheStrategy;

public class UserCacheImpl implements UserCache {
    private CacheStrategy cacheStrategy;
    private Cache cache;

    public UserDTO findById(String id) {
        String key = "userId:" + id;

        return cache.get(key);
    }

    public UserDTO findByUsername(String username, String userRepoRef) {
        String key = "userUsername:" + username + ":" + userRepoRef;

        return cache.get(key);
    }

    public UserDTO findByRef(String ref, String userRepoRef) {
        String key = "userRef:" + ref + ":" + userRepoRef;

        return cache.get(key);
    }

    public void updateUser(UserDTO userDto) {
        cache.set("userId:" + userDto.getId(), userDto);
        cache.set(
                "userUsername:" + userDto.getUsername() + ":"
                        + userDto.getUserRepoRef(), userDto);
        cache.set(
                "userRef:" + userDto.getRef() + ":" + userDto.getUserRepoRef(),
                userDto);
    }

    public void removeUser(UserDTO userDto) {
        cache.remove("userId:" + userDto.getId());
        cache.remove("userUsername:" + userDto.getUsername() + ":"
                + userDto.getUserRepoRef());
        cache.remove("userRef:" + userDto.getRef() + ":"
                + userDto.getUserRepoRef());
    }

    public void setCacheStrategy(CacheStrategy cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
        this.cache = cacheStrategy.getCache("user");
    }
}
