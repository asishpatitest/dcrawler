package com.nvharikrishna.cache;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Harikrishna on 18/02/17.
 */
public class CrawledURLsRedisCacheTest {
    CrawledURLsCache crawledURLsCache = new CrawledURLsRedisCache();

    @Test
    public void add() throws Exception {
        String url = "/home";
        crawledURLsCache.add(url);
        assertTrue(crawledURLsCache.hasFinished(url));
    }

    @Test
    public void hasFinished() throws Exception {
        String url = "/hasFinished";
        crawledURLsCache.add(url);
        assertFalse(crawledURLsCache.hasFinished(url + "randommmm"));

    }

}