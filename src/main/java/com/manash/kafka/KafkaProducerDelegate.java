package com.manash.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class KafkaProducerDelegate  {
    private final Logger logger = LoggerFactory.getLogger(KafkaProducerDelegate.class);

    Properties props;

    //public void execute(DelegateExecution execution) throws Exception{
        public void execute() throws Exception{

       /* String content = (String) execution.getVariable("content");
        if(content.equals("NE")){
            throw new RuntimeException("No Content found to publish");
        }

        String TopicName = (String) execution.getVariable("TopicName");
        if(content.equals("")){
            throw new RuntimeException("No Topic found to publish");
        }*/
            String content = "Hello";
            String TopicName = "Camunda-TestProcess-events";

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

        //for(int i = 0; i < 10; i++)
        //    producer.send(new ProducerRecord<String, String>(TopicName, Integer.toString(i), Integer.toString(i)));
            producer.send(new ProducerRecord<String, String>(TopicName, "Gogo", "Gogo"));
        System.out.println("Message sent successfully");
        producer.close();

    }
}
