package cn.adbyte.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.concurrent.ListenableFuture;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Adam
 */
@Slf4j
@EnableSwagger2
@EnableScheduling
@SpringCloudApplication
public class KafkaApplication {

    @Autowired
    private KafkaTemplate<String, String> sysLogKafkaTemplate;
    @Autowired
    private ObjectMapper mapper;

    @Bean
    public ProducerFactory<String, String> kafkaProducerFactory(KafkaProperties properties) {
        return new DefaultKafkaProducerFactory<>(properties.buildProducerProperties());
    }

    @Bean
    public KafkaTemplate<String, String> sysLogKafkaTemplate(KafkaProperties properties) {
        return new KafkaTemplate<>(kafkaProducerFactory(properties));
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

    @KafkaListener(topics = "topic", groupId = "group")
    public void listen(ConsumerRecord<String, String> cr) {
        log.warn(cr.getClass().getName());
        log.warn(cr.toString());
        log.warn(cr.value().getClass().getName());
        log.info("[ " + cr.value() + " ]");
        log.warn("+++++++++++++++++++++++++++++++++++++");
    }

    @Scheduled(cron = "0/3 * * * * ?")
    public void sendTest() throws JsonProcessingException {
        Foo param = Foo.builder().id(1L).params("param").time(LocalDateTime.now()).build();

        ListenableFuture<SendResult<String, String>> test = sysLogKafkaTemplate.send("topic", mapper.writeValueAsString(param));
        log.info(test.toString());
    }

}
