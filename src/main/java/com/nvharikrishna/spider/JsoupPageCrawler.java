package com.nvharikrishna.spider;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harikrishna on 19/02/17.
 */
class JsoupPageCrawler implements  PageCrawler{

    private static final Logger logger = LoggerFactory.getLogger(JsoupPageCrawler.class);

    public List<String> fetchLinks(String url) throws CannotFetchPage {
        if(null == url)
            return null;

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        }catch (HttpStatusException hse){
            int statusCode = hse.getStatusCode();
            throw  new CannotFetchPage("Error while fetching url [" + url + "]", statusCode, hse);
        } catch (IOException e) {
            throw new CannotFetchPage("Failed to fetch url: " + url , e);
        }

        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        if(logger.isDebugEnabled()){
            logger.debug("Number of links in URL [" + url + "] are: " + links.size());
            logger.debug("Number of media in URL [" + url + "] are: " + media.size());
            logger.debug("Number of imports in URL [" + url + "] are: " + imports.size());
        }

        List<String> linkedUrls = new ArrayList<String>();
        for (Element link : links) {
            String linkStr = link.attr("abs:href");
            if(StringUtils.isNotBlank(linkStr))
                linkedUrls.add(linkStr);
        }
        for(Element mediaLink: links){
            //TODO: do we need to do any filtering.
            String linkStr = mediaLink.attr("abs:src");
            if(StringUtils.isNotBlank(linkStr))
                linkedUrls.add(linkStr);
        }
        for(Element importLink: imports){
            String linkStr = importLink.attr("abs:href");
            if(StringUtils.isNotBlank(linkStr))
                linkedUrls.add(linkStr);
        }

        return linkedUrls;
    }

    public static void main(String[] args){
        String url = "https://www.bankbazaar.com/reviews.html";
        JsoupPageCrawler crawler = new JsoupPageCrawler();
        try {
            List<String> links = crawler.fetchLinks(url);
            for(String link: links)
                System.out.println("Found link : " + link);

        } catch (CannotFetchPage cannotFetchPage) {
            cannotFetchPage.printStackTrace();
        }
    }
}
