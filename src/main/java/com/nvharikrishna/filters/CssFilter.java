package com.nvharikrishna.filters;

import javax.swing.text.html.CSS;

/**
 * Created by Harikrishna on 18/02/17.
 */
public class CssFilter implements Filter<String> {

    public String doProcess(String message) {
        if(message.endsWith(".css")){
            //don't do anything
            return BREAK;
        }

        return CONTINUE;

    }
}
