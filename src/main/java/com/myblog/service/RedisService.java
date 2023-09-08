package com.myblog.service;

public interface RedisService {
    Boolean expire(String key, long time);
    Boolean hSet(String key, String hashKey, Object value, long time);
    Object hGet(String key, String hashKey);
    void hDel(String key, Object... hashKey);
}
