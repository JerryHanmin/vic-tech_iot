package com.vic.iot.netty.server.repository;

import io.netty.channel.Channel;

public interface ChannelRepository {
    void put(String key, Channel value);

    Channel get(String key);

    void remove(String key);

    int count();
}
