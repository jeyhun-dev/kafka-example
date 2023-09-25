package az.cmammad.config;

import az.cmammad.enumeration.KafkaConst;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topicMessage() {
        return TopicBuilder.name(KafkaConst.TOPIC_SUPERHERO.name())
                .partitions(3)
                .replicas(1)
                .build();
    }
}
