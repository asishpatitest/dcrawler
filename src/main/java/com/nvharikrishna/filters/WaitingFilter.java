package com.nvharikrishna.filters;


import com.nvharikrishna.cache.WaitingUrlsCache;
import com.nvharikrishna.cache.WaitingUrlsCacheImpl;

/**
 * Created by abinash on 19/2/17.
 */
public class WaitingFilter implements Filter<String>{

    public String doProcess(String message) {
        WaitingUrlsCache waitingUrlsCache = new WaitingUrlsCacheImpl();
        if(waitingUrlsCache.find(message)) {
            return BREAK;
        }
        return CONTINUE;
    }

}
