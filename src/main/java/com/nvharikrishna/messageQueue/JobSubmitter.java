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

    private static final String TOPIC = "URL-QUEUE";

    public static void main(String args[]) {
        if(args.length > 0) {
            String url = args[0];
            Properties props = new Properties();
            props.put("bootstrap.servers", "localhost:9092");
            props.put("acks", "1");
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            Producer<String, String> producer = new KafkaProducer<String, String>(props);
            Future<RecordMetadata> metadataFuture =  producer.send(new ProducerRecord<String, String>(TOPIC, url));
            producer.close();
        }
    }

}
