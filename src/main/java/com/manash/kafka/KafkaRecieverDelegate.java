package com.manash.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Properties;


    public class KafkaRecieverDelegate  implements JavaDelegate {

    private final Logger logger = LoggerFactory.getLogger(KafkaProducerDelegate.class);
    Properties props;
    public void execute(DelegateExecution execution) throws Exception{

        String TopicName = "Camunda-TestProcess-events";
        String content = "Hello World from Camunda";

        props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        //props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        readMessage("Camunda-TestProcess-events");
    }

    void readMessage(String TopicName){
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        //Kafka Consumer subscribes list of topics here.
        consumer.subscribe(Arrays.asList(TopicName));

        System.out.println("Subscribed to topic " + TopicName);
        int i = 0;
        int y =0;
        while (true) {
            if(y==1)
                break;
            ConsumerRecords<String, String> records = consumer.poll(5000);
            for (ConsumerRecord<String, String> record : records) {
                // print the offset,key and value for the consumer records.
                System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
                if (record.value().equals("Quit1")){
                    y = 1;
                    System.out.printf("Print "+y);
                }
            }
        }
    }
}
