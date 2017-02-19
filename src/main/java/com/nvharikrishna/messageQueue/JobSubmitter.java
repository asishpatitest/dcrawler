package com.nvharikrishna.messageQueue;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * Created by abinash on 18/2/17.
 */
public class JobSubmitter {

    private static final String TOPIC = "URL_QUEUE";

    public static void main(String args[]) {
        if(args.length > 0) {
            String url = args[0];
            Producer<String, String> producer = KafkaUtils.initialiseProducer();
            producer.send(new ProducerRecord<String, String>(TOPIC, url));
            producer.close();
        }
    }

}
