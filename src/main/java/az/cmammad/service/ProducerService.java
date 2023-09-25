package az.cmammad.service;

import az.cmammad.enumeration.KafkaConst;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProducerService {

    private static final Logger LOG = LoggerFactory.getLogger(ProducerService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @SneakyThrows
    public void sendMessage(String message) {
        int a = 0;
        while (true) {
            a++;
            Thread.sleep(1000);
            LOG.info("#### -> Publish with producer message -> {}", message);
            kafkaTemplate.send(KafkaConst.TOPIC_SUPERHERO.name(), message);
            if (a == 10) {
                Thread.currentThread().wait();
            }
        }
    }
}
