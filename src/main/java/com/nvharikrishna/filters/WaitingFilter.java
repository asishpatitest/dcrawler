package com.nvharikrishna.filters;


import com.nvharikrishna.cache.WaitingUrlsCache;
import com.nvharikrishna.cache.WaitingUrlsCacheImpl;

/**
 * Created by abinash on 19/2/17.
 */
public class WaitingFilter implements Filter<String>{
    WaitingUrlsCache waitingUrlsCache = new WaitingUrlsCacheImpl();

    public String doProcess(String message) {

        return waitingUrlsCache.find(message) ? BREAK : CONTINUE;
    }

}
