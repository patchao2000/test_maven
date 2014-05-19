package com.videostar.ext.message;

public interface Subscribable<T> {
    void handleMessage(T message);

    String getTopic();
}
