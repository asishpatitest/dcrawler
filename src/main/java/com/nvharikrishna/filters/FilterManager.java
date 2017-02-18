package com.nvharikrishna.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harikrishna on 18/02/17.
 */
public class FilterManager implements Filter<String> {

    List<Filter<String>> filters = new ArrayList<Filter<String>>();

    private static final Logger logger = LoggerFactory.getLogger(FilterManager.class);

    public FilterManager(){

        WorkerQueueSubmitFilter workerQueueSubmitFilter = new WorkerQueueSubmitFilter();
        CssFilter cssFilter = new CssFilter(); //first filter

        filters.add(cssFilter);
        filters.add(workerQueueSubmitFilter);
    }

    public static void main(String[] args){
        Filter<String> filter = new FilterManager();
        filter.doProcess("/home-loan.html");
    }

    public String doProcess(String message) {
        for(Filter<String> filter: filters){
            try {
                String status = filter.doProcess(message);

                if(Filter.BREAK.equals(status))
                    break;
            } catch (Exception e){
                logger.error("Error while processing filters.", e);
            }
        }

        return CONTINUE; //FIXME: remove it. Doesn't look good.
    }
}
