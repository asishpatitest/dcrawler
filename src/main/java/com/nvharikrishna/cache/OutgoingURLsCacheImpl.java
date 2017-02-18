package com.nvharikrishna.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * Created by abinash on 18/2/17.
 */
public class OutgoingURLsCacheImpl implements OutgoingURLsCache {

    private final static Logger logger = LoggerFactory.getLogger(OutgoingURLsCacheImpl.class);
    private static Jedis jedis = JedisClient.getJedis();
    private static final String KEY = "OUTGOING_URL_CACHE";

    public void add(String key, String value) {
        jedis.sadd(KEY + ":" + key, value);
    }

    public Set<String> fetchAll(String key) {
        return jedis.smembers(KEY + ":" + key);
    }
}
