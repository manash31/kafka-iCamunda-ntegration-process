package com.manash.workflow;

import com.manash.kafka.KafkaProducerDelegate;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  public static void main(String... args) throws Exception {


    //SpringApplication.run(Application.class, args);

    KafkaProducerDelegate kd = new KafkaProducerDelegate();
    kd.execute();

  }

}