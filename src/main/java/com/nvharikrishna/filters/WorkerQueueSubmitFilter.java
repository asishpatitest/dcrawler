package com.nvharikrishna.filters;

/**
 * Created by Harikrishna on 18/02/17.
 */
public class WorkerQueueSubmitFilter implements Filter<String > {
    public String doProcess(String message) {
        //submit to next queue
        return CONTINUE;
    }
}
