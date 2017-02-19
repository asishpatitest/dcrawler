package com.nvharikrishna.spider;

import java.util.List;

/**
 * This is a util class for crawling a url.
 * Created by Harikrishna on 19/02/17.
 */
public interface PageCrawler {

    /**
     * Fetches links in a page located by URL.
     * @param url
     * @return
     */
    public List<String> fetchLinks(String url) throws CannotFetchPage;
}
