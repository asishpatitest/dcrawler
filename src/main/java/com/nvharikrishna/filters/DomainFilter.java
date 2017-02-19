package com.nvharikrishna.filters;

import com.nvharikrishna.AppConfig;

/**
 * This class is for filtering URL which are not white listed.
 * Created by abinash on 19/2/17.
 */
public class DomainFilter implements Filter<String> {

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
            //TODO: do below check only for hostname part of the URL.
            if(!message.contains(allowedUrl[index])) {
                return BREAK;
            }
        }
        return CONTINUE;

    }

}
