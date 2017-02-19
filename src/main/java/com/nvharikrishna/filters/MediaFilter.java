package com.nvharikrishna.filters;

/**
 * Filters media files like css/jpg/js/png.
 *
 * Created by Harikrishna on 18/02/17.
 */
public class MediaFilter implements Filter<String> {

    /**
     * Fiters media files.
     * @param message
     * @return
     * BREAK - If media file is found
     * CONTINUE - If the URL is a not a media
     */
    public String doProcess(String message) {
        if(message.endsWith(".css") || message.endsWith(".jpg") || message.endsWith(".js") || message.endsWith(".png")){
            //TODO: read from configuration file.
            return BREAK;
        }

        return CONTINUE;

    }
}
