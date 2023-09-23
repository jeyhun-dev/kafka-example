package az.cmammad.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topicMessage() {
        return TopicBuilder.name("TOPIC-STRING")
                .partitions(4)
                .replicas(1)
                .build();
    }
}
