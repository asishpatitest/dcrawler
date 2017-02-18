package com.nvharikrishna.cache;

import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * Created by Harikrishna on 18/02/17.
 */
public class ErrorURLsRedisCache implements ErrorURLsCache {
    private static final Jedis jedis = JedisClient.getJedis();

    static final String CACHE_KEY = "errorUrls";

    public void add(String URL, String status) {
        jedis.hset(CACHE_KEY, URL, status);
    }

    public Map<String, String> fetchAll() {
        return jedis.hgetAll(CACHE_KEY);
    }
}
