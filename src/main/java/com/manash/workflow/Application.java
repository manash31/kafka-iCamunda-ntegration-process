package com.manash.workflow;

import com.manash.kafka.KafkaProducerDelegate;
import com.manash.kafka.KafkaRecieverDelegate;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  public static void main(String... args) throws Exception {

    SpringApplication.run(Application.class, args);
   // KafkaRecieverDelegate kr = new KafkaRecieverDelegate();
    //kr.execute();

  }

}