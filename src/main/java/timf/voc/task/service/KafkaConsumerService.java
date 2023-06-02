package timf.voc.task.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(topics = "new-voc")
    public void handleNewVocNotification(String message) {
        log.info("Received Kafka message: " + message);
        // todo : invoke app push alert
    }
}
