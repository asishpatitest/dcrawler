package com.nvharikrishna.spider;

import java.io.IOException;

/**
 * Created by Harikrishna on 19/02/17.
 */
public class CannotFetchPage extends Exception {
    int statusCode;

    public CannotFetchPage(String message, int statusCode, Throwable throwable){
        super(message, throwable);
        this.statusCode = statusCode;
    }

    public CannotFetchPage(String message, Throwable e) {
        super(message, e);
    }

    public int getStatusCode(){
        return statusCode;
    }
}
