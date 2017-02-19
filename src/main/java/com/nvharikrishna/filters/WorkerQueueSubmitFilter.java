package com.nvharikrishna.filters;

import com.nvharikrishna.cache.WaitingUrlsCache;
import com.nvharikrishna.cache.WaitingUrlsCacheImpl;
import com.nvharikrishna.messageQueue.KafkaUtils;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Harikrishna on 18/02/17.
 */
public class WorkerQueueSubmitFilter implements Filter<String > {

    private static final String TOPIC = "WAITING_QUEUE";
    WaitingUrlsCache waitingUrlsCache = new WaitingUrlsCacheImpl();
    private static final Logger logger = LoggerFactory.getLogger(WorkerQueueSubmitFilter.class);

    public String doProcess(String message) {
        try {
            Producer<String, String> producer = KafkaUtils.initialiseProducer();
            producer.send(new ProducerRecord<String, String>(TOPIC, message));
            producer.close();

            waitingUrlsCache.add(message);
        } catch(Exception e) {
            logger.error("Exception while pushing the url to topic : ", e);
            return BREAK;
        }
        return CONTINUE;
    }
}
