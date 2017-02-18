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
        String url = "/home.html";
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "1");
//        props.put("retries", 0);
//        props.put("batch.size", 16384);
//        props.put("linger.ms", 1);
//        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        Future<RecordMetadata> metadataFuture =  producer.send(new ProducerRecord<String, String>(TOPIC, url, url));
        System.out.println("is done = " + metadataFuture.isDone());
        System.out.println(metadataFuture.toString());
        System.out.println("Message sent successfully");
        producer.close();
    }

}
