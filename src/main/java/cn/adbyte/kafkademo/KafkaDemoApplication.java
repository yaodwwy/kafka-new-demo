package cn.adbyte.kafkademo;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Properties;

@Slf4j
@SpringBootApplication
public class KafkaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaDemoApplication.class, args);
    }

    @KafkaListener(topics = "myTopic")
    public void listen(ConsumerRecord<String, String> cr) {

        log.info("---NO." + cr.offset() + "---" + cr.toString());
    }

    @KafkaListener(topics = "myTopic-new")
    public void listenmyTopicNew(ConsumerRecord<String, String> cr) {

        log.info("---NO." + cr.offset() + "---" + cr.toString());
    }
}
