package com.nvharikrishna.cache;

import com.nvharikrishna.AppConfig;
import redis.clients.jedis.Jedis;

/**
 * Created by Harikrishna on 18/02/17.
 */
public class JedisClient {
    private static volatile Jedis jedis;
    private static final Object lock = new Object();

    public static Jedis getJedis(){
        if(null == jedis){
            synchronized (lock){
                if(null == jedis){
                    String[] hosts = AppConfig.get("redis.hosts").split(",");
                    jedis = new Jedis(hosts[0]);

                }
            }
        }

        return jedis;
    }
}
