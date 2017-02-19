package com.nvharikrishna.filters;

import com.nvharikrishna.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

/**
 * This class is for filtering URL which are not white listed.
 * Created by abinash on 19/2/17.
 */
public class DomainFilter implements Filter<String> {

    private static final Logger logger = LoggerFactory.getLogger(DomainFilter.class);
    String[] allowedUrl = null;

    public DomainFilter(){
        allowedUrl = AppConfig.get("allowedDomain").split(",");
    }

    /**
     * This method filters out the URLs which are not white listed.
     * @param message
     * @return
     * BREAK - If URL is not white listed
     * CONTINUE - If URL can be crawled
     */
    public String doProcess(String message) {

        for(int index=0;index<allowedUrl.length;index++) {
            try {
                URI uri = new URI(message);
                String domain = uri.getHost();
                String url = domain.startsWith("www.") ? domain.substring(4) : domain;
                if (url.contains(allowedUrl[index])) {
                    return CONTINUE;
                }
            } catch(Exception e) {
                logger.error("Exception for URL [" + message + "],", e);
                return BREAK;
            }
        }
        return BREAK;
    }

}
