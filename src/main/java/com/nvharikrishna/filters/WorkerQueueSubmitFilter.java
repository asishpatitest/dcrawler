package com.nvharikrishna.filters;

import com.nvharikrishna.cache.WaitingUrlsCache;
import com.nvharikrishna.cache.WaitingUrlsCacheImpl;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * Created by Harikrishna on 18/02/17.
 */
public class WorkerQueueSubmitFilter implements Filter<String > {

    private static final String TOPIC = "WAITING-QUEUE";
    WaitingUrlsCache waitingUrlsCache = new WaitingUrlsCacheImpl();
    private static final Logger logger = LoggerFactory.getLogger(WorkerQueueSubmitFilter.class);

    public String doProcess(String message) {
        try {
            Properties props = new Properties();
            props.put("bootstrap.servers", "localhost:9092"); //TODO: read this from config
            props.put("acks", "1");
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            Producer<String, String> producer = new KafkaProducer<String, String>(props); //TODO: do we have to create all them time?
            producer.send(new ProducerRecord<String, String>(TOPIC, message));
            producer.close();

            waitingUrlsCache.add(message);

        } catch(Exception e) {
            //TODO: add logging
            return BREAK;
        }
        //submit to next queue
        return CONTINUE;
    }
}
