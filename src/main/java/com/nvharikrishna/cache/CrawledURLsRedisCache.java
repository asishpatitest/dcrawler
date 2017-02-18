package com.nvharikrishna.cache;

import redis.clients.jedis.Jedis;

/**
 * Created by Harikrishna on 18/02/17.
 */
public class CrawledURLsRedisCache implements CrawledURLsCache {
    private static final Jedis jedis = JedisClient.getJedis();

    static final String CRAWLED_URLS_MAP = "crawledUrls";

    public void add(String url) {
        jedis.sadd(CRAWLED_URLS_MAP, url);
    }

    public boolean hasFinished(String url) {
        return jedis.sismember(CRAWLED_URLS_MAP, url);
    }
}
