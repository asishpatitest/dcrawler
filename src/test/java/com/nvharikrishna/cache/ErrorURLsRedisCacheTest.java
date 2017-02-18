package com.nvharikrishna.cache;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Harikrishna on 18/02/17.
 */
public class ErrorURLsRedisCacheTest {
    ErrorURLsCache errorURLsCache = new ErrorURLsRedisCache();

    @Test
    public void add() throws Exception {
        String url = "/home.html";
        errorURLsCache.add(url, "200");
        Map<String,String> map = errorURLsCache.fetchAll();
        assertEquals(map.size(), 1);
    }

    @Test
    public void fetchAll() throws Exception {
        int size = errorURLsCache.fetchAll().size();
        String url = "/" + System.currentTimeMillis();
        errorURLsCache.add(url, "404");
        assertEquals(errorURLsCache.fetchAll().size(), size + 1);
    }

}