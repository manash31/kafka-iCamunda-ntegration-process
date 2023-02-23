package com.manash.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class KafkaProducerDelegate implements JavaDelegate {
    private final Logger logger = LoggerFactory.getLogger(KafkaProducerDelegate.class);

    Properties props;

    public void execute(DelegateExecution execution) throws Exception{

       String content = (String) execution.getVariable("content");
        if(content.equals("")){
            throw new RuntimeException("No Content found to publish");
        }

        String TopicName = (String) execution.getVariable("TopicName");
        if(TopicName.equals("")){
            throw new RuntimeException("No Topic found to publish");
        }
        System.out.printf("TopicName :"+TopicName);
        System.out.printf("content :"+content);
        //String TopicName = "Camunda-TestProcess-events";
        //String content = "Hello World from Camunda";

        props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        //If the request fails, the producer can automatically retry,
        props.put("retries", 0);
        //Specify buffer size in config
        props.put("batch.size", 16384);
        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);
        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        producer.send(new ProducerRecord<String, String>(TopicName, content,content ));
        logger.info("Message sent successfully to Topic"+TopicName+" content"+content);
        producer.close();

    }
}
