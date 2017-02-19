package com.nvharikrishna.filters;

import com.nvharikrishna.AppConfig;

/**
 * Filters media files like css/jpg/js/png.
 *
 * Created by Harikrishna on 18/02/17.
 */
public class MediaFilter implements Filter<String> {

    String[] blackListedMedia = null;

    public MediaFilter(){
        blackListedMedia = AppConfig.get("blacklistedMedia").split(",");
    }

    /**
     * Fiters media files.
     * @param message
     * @return
     * BREAK - If media file is found
     * CONTINUE - If the URL is a not a media
     */
    public String doProcess(String message) {
        for(int index=0; index<blackListedMedia.length; index++) {
            if(message.endsWith(blackListedMedia[index])) {
                return BREAK;
            }
        }
        return CONTINUE;

    }
}
