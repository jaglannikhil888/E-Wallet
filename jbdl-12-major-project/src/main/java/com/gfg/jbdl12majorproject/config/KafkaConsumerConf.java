package com.gfg.jbdl12majorproject.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConf {

    @Bean
    Map<String, Object> consumerProperties(){
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return properties;
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory
                = new ConcurrentKafkaListenerContainerFactory<>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
        return concurrentKafkaListenerContainerFactory;

    }

    @Bean
    ConsumerFactory<String, String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerProperties());
    }



}
