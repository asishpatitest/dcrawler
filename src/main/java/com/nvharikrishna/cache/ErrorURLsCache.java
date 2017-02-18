package com.nvharikrishna.cache;

import java.util.Map;

/**
 * Represents error URLs cache.
 *
 * Created by abinash on 18/2/17.
 */
public interface ErrorURLsCache {

    /**
     * Adds given URL and status of the URL to error URL cache.
     * @param URL
     * @param status
     */
    public void add(String URL, String status);


    /**
     * Returns all URLs and their status present in the cache.
     * @return
     */
    public Map<String, String> fetchAll();
}
