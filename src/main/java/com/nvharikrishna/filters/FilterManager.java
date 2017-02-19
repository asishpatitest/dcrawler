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
        MediaFilter mediaFilter = new MediaFilter(); //first filter
        DomainFilter domainFilter = new DomainFilter();
        CrawledFilter crawledFilter = new CrawledFilter();
        WaitingFilter waitingFilter = new WaitingFilter();

        filters.add(mediaFilter);
        filters.add(domainFilter);
        filters.add(crawledFilter);
        filters.add(waitingFilter);
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
                logger.error("Error while processing URL [" + message + "] filters.", e);
            }
        }

        return CONTINUE; //FIXME: remove it. Doesn't look good.
    }
}
