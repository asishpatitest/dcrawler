package com.nvharikrishna.messageQueue;

import com.nvharikrishna.AppConfig;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;

/**
 * Created by abinash on 19/2/17.
 */
public class KafkaUtils {

    private static String[] hosts = AppConfig.get("kafka.hosts").split(",");

    public static Producer<String, String> initialiseProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", hosts[0]);
        props.put("acks", "1");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return new KafkaProducer<String, String>(props);
    }

    public static Consumer<String, String> initialiseConsumer(String topic, String group) {
        Properties props = new Properties();
        props.put("bootstrap.servers", hosts[0]);
        props.put("group.id", group);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return new KafkaConsumer<String, String>(props);
    }
}
