package com.nvharikrishna.cache;

/**
 * Represents cache of crawled URLs.
 *
 * Created by abinash on 18/2/17.
 */
public interface CrawledURLsCache {

    /**
     * Adds url to list of crawled URLs in cache.
     * @param url
     */
    public void add(String url);

    /**
     * Tell if URL is already in crawled list or not.
     * @param url
     * @return
     * True - if URL is already crawled.
     * False - if URL is not crawled.
     */
    public boolean hasFinished(String url);

}
