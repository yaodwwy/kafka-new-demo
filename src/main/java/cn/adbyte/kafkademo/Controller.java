package cn.adbyte.kafkademo;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Adam
 */
@Slf4j
@RestController
public class Controller {

    @Autowired
    private KafkaTemplate<String, String> template;

    @RequestMapping("/")
    public String hi() {

        for (int i = 0; i < 5; i++) {
            this.template.send("myTopic", i + "我来了");
        }
        return "hi" ;
    }

}
