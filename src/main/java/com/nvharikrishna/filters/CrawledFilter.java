package com.nvharikrishna.filters;


import com.nvharikrishna.cache.CrawledURLsCache;
import com.nvharikrishna.cache.CrawledURLsRedisCache;

/**
 * Checks if the URL is already crawled or not.
 *
 * Created by abinash on 19/2/17.
 */
public class CrawledFilter implements Filter<String>{
    CrawledURLsCache crawledURLsCache = new CrawledURLsRedisCache();

    /**
     * Check with cache if the URL is already crawled or not.
     * @param message
     * @return
     *  BREAK - If it is already crawled
     *  CONTINUE - If URL is not crawled
     */
    public String doProcess(String message) {

        return crawledURLsCache.hasFinished(message) ? BREAK : CONTINUE;
    }
}
