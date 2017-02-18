package com.nvharikrishna.cache;

import com.nvharikrishna.AppConfig;
import redis.clients.jedis.Jedis;

/**
 * Follows singleton patterns for creating Jedis client. We don't need git new instance of Jedis instance all the time.
 *
 * Created by Harikrishna on 18/02/17.
 */
public class JedisClient {
    private static volatile Jedis jedis;
    private static final Object lock = new Object();

    /**
     * Singleton method for getting the instance of Jedis.
     *
     * @return returns Jedis client if successfully initialized. Otherwise returns null.
     */
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
