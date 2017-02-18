package com.nvharikrishna.cache;

/**
 * Created by abinash on 18/2/17.
 */
public interface WaitingUrlsCache {

    void add(String value);

    void remove(String value);

    Boolean find(String value);
}
