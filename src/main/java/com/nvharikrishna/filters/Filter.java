package com.nvharikrishna.filters;

/**
 * Created by Harikrishna on 18/02/17.
 */
public interface Filter<T> {

    public final String CONTINUE = "CONTINUE";
    public final String BREAK = "BREAK";

    /**
     * Process the request
     * @param message
     * @return
     * CONTINUE : to coninue to next filter
     * BREAK    : to stop processing the message by next filter
     */
    public String doProcess( T message);
}
