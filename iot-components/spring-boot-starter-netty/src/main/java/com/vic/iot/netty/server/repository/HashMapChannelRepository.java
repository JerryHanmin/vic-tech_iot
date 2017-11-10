package com.vic.iot.netty.server.repository;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Channel Manager
 */
public class HashMapChannelRepository implements ChannelRepository {
    private static final Map<String, Channel> CHANNEL_CACHE = new ConcurrentHashMap<>();

    @Override
    public void put(String key, Channel value) {
        CHANNEL_CACHE.put(key, value);
    }

    @Override
    public Channel get(String key) {
        return CHANNEL_CACHE.get(key);
    }

    @Override
    public void remove(String key) {
        CHANNEL_CACHE.remove(key);
    }

    @Override
    public int count() {
        return CHANNEL_CACHE.size();
    }
}
