package az.cmammad.service;

import az.cmammad.enumeration.KafkaConst;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProducerService {

    private static final Logger LOG = LoggerFactory.getLogger(ProducerService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        for (int a = 0; a < 99; a++) {
            LOG.info("#### -> Publish with producer message -> {}", message);
            kafkaTemplate.send(KafkaConst.TOPIC_SUPERHERO.name(), message);
        }
    }
}
