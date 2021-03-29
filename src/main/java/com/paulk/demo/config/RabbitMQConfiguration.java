package com.paulk.demo.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Defines a set of {@link Exchange} for the Entry API.
 */
@Configuration
public class RabbitMQConfiguration {

    /**
     * Creates an {@link TopicExchange} for the Add Entry interaction.
     *
     * @return The {@link TopicExchange} to be created.
     */
    @Bean
    public Exchange getEntryAddExchange() {
        return new TopicExchange("entryAddExchange");
    }

    /**
     * Creates an {@link TopicExchange} for the Delete Entry interaction.
     *
     * @return The {@link TopicExchange} to be created.
     */
    @Bean
    public Exchange getEntryDeleteExchange() {
        return new TopicExchange("entryDeleteExchange");
    }

    /**
     * Creates an {@link TopicExchange} for the Update Entry interaction.
     *
     * @return The {@link TopicExchange} to be created.
     */
    @Bean
    public Exchange getEntryUpdateExchange() {
        return new TopicExchange("entryUpdateExchange");
    }
}
