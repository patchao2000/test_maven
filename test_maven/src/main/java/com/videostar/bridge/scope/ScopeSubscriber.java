package com.videostar.bridge.scope;

import javax.annotation.Resource;

import com.videostar.api.scope.ScopeCache;
import com.videostar.api.scope.ScopeDTO;

import com.videostar.core.mapper.JsonMapper;

import com.videostar.ext.message.Subscribable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScopeSubscriber implements Subscribable<String> {
    private static Logger logger = LoggerFactory
            .getLogger(ScopeSubscriber.class);
    private JsonMapper jsonMapper = new JsonMapper();
    private ScopeCache scopeCache;
    private String destinationName = "topic.scope.update";

    public void handleMessage(String message) {
        ScopeDTO scopeDto = jsonMapper.fromJson(message, ScopeDTO.class);

        if (scopeDto.getName() == null) {
            scopeCache.removeScope(scopeDto);
            logger.info("remove scopeDto : {}", message);
        } else {
            scopeCache.updateScope(scopeDto);
            logger.info("update scopeDto : {}", message);
        }
    }

    public String getTopic() {
        return destinationName;
    }

    @Resource
    public void setScopeCache(ScopeCache scopeCache) {
        this.scopeCache = scopeCache;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }
}
