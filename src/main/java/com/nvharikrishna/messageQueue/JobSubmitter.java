package com.nvharikrishna.messageQueue;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

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
            props.put("acks", "all");
            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("linger.ms", 1);
            props.put("buffer.memory", 33554432);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            Producer<String, String> producer = new KafkaProducer<String, String>(props);
            producer.send(new ProducerRecord<String, String>(TOPIC, url));
            System.out.println("Message sent successfully");
            producer.close();
        }
    }

}
