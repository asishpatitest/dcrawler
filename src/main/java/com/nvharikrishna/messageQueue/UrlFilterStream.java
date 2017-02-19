package com.nvharikrishna.messageQueue;

import com.nvharikrishna.filters.FilterManager;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Arrays;

/**
 * Created by abinash on 18/2/17.
 */
public class UrlFilterStream extends Thread {

    private static final String TOPIC = "URL_QUEUE";
    private FilterManager filterManager = new FilterManager();

    public void run(){
        Consumer<String, String> consumer = KafkaUtils.initialiseConsumer(TOPIC, "default");
        consumer.subscribe(Arrays.asList(TOPIC));
        System.out.println("Subscribed to topic " + TOPIC);
        int i = 0;

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                filterManager.doProcess(record.value());
            }
        }
    }

    public static void main(String[] args){
        UrlFilterStream urlFilterStream = new UrlFilterStream();

        //Making user thread t1 to Daemon
        urlFilterStream.setDaemon(true);

        //starting both the threads
        urlFilterStream.start();
    }

}
