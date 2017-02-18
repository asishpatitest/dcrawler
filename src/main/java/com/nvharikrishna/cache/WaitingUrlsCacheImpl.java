package com.nvharikrishna.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by abinash on 18/2/17.
 */
public class WaitingUrlsCacheImpl implements WaitingUrlsCache {

    private final static Logger logger = LoggerFactory.getLogger(WaitingUrlsCacheImpl.class);
    private static Jedis jedis = JedisClient.getJedis();
    private static final String KEY = "WAITING_URL_CACHE";

    public void add(String value) {
        try {
            if (value != null) {
                jedis.sadd(KEY, value);
            }
        } catch (Exception e) {
            logger.error("Error while adding map to hash ", e);
        }
    }

    public Boolean find(String value) {
        return jedis.sismember(KEY, value);
    }

    public void remove(String value) {
        jedis.srem(KEY, value);
    }

}
