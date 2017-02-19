package com.nvharikrishna.filters;


import com.nvharikrishna.cache.CrawledURLsCache;
import com.nvharikrishna.cache.CrawledURLsRedisCache;

/**
 * Created by abinash on 19/2/17.
 */
public class CrawledFilter implements Filter<String>{
    public String doProcess(String message) {
        CrawledURLsCache crawledURLsCache = new CrawledURLsRedisCache();
        if(crawledURLsCache.hasFinished(message)) {
            return BREAK;
        }
        return CONTINUE;
    }
}
