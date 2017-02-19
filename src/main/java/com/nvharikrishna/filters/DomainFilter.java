package com.nvharikrishna.filters;

import com.nvharikrishna.AppConfig;

/**
 * Created by abinash on 19/2/17.
 */
public class DomainFilter implements Filter<String> {

    public String doProcess(String message) {
        String[] allowedURL = AppConfig.get("allowedDomain").split(",");
        for(int index=0;index<allowedURL.length;index++) {
            if(!message.contains(allowedURL[index])) {
                return BREAK;
            }
        }
        return CONTINUE;

    }

}
