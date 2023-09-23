package az.cmammad.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerService {

    private static final Logger LOG = LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(
            topics = "TOPIC_SUPERHERO",
            containerFactory = "stringKafkaListenerFactory",
            groupId = "superhero")
    public void consumeMessage(String message) {
        LOG.info("**** -> Consume with Consumer message -> {}", message);
    }
}
