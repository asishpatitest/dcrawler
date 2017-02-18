package com.nvharikrishna.cache;

import java.util.Set;

/**
 * Created by abinash on 18/2/17.
 */
public interface IncomingURLsCache {

    void add(String key, String value);

    Set<String> fetchAll(String key);
    
}
