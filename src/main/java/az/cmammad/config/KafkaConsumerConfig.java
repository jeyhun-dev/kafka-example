package az.cmammad.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    @Value("${spring.kafka.brokers.bootstrap-servers}")//bura 9092 yazilmadi
    private String bootstrapServers;

    @Value("${spring.kafka.brokers.group-id}")
    private String groupId;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> stringKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> containerFactory =
                new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(stringConsumerFactory());
//        containerFactory.setRecordMessageConverter(new StringJsonMessageConverter());
        containerFactory.setBatchListener(true);
        return containerFactory;
    }

    @Bean
    public ConsumerFactory<String, String> stringConsumerFactory() {
        Map<String, Object> configMaps = new HashMap<>();
        configMaps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configMaps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configMaps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configMaps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        configMaps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configMaps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(
                configMaps,
                new StringDeserializer(),
                new StringDeserializer());
    }

    @Bean
    public KafkaListenerErrorHandler myTopicErrorHandler() {
        return (a, b) -> {
            LOG.error("This is error {}", b.getMessage());
            return "info about the failure";
        };
    }
}
