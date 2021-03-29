package com.paulk.demo.service;

import com.paulk.demo.config.RabbitMQConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A {@link Service} for sending an Entry to a {@link Exchange} using the {@link RabbitTemplate}.
 */
@Service
public class EntryQueueService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EntryQueueService.class);
    private static final String ADD_ENTRY_ROUTING_KEY = "entry.add";
    private static final String DELETE_ENTRY_ROUTING_KEY = "entry.delete";
    private static final String UPDATE_ENTRY_ROUTING_KEY = "entry.update";


    @Autowired
    RabbitMQConfiguration rabbitMQConfiguration;

    @Autowired
    protected RabbitTemplate rabbitTemplate;

    /**
     * Adds the {@link T} to the add {@link Exchange}.
     *
     * @param entry - The {@link T} to be processed.
     * @param <T>   An {@link Object} to be added to the {@link Exchange}.
     */
    public <T> boolean addEntry(T entry) {
        Exchange exchange = rabbitMQConfiguration.getEntryAddExchange();
        try {
            rabbitTemplate.convertAndSend(exchange.getName(), ADD_ENTRY_ROUTING_KEY, entry);
            return true;
        } catch (AmqpException exception) {
            LOGGER.error("Error adding Entry to Add Entry Exchange", exception);
        }
        return false;
    }

    /**
     * Adds the {@link T} to the delete {@link Exchange}.
     *
     * @param entry - The {@link T} to be processed.
     * @param <T>   An {@link Object} to be added to the {@link Exchange}.
     */
    public <T> boolean deleteEntry(T entry) {
        Exchange exchange = rabbitMQConfiguration.getEntryDeleteExchange();
        try {
            rabbitTemplate.convertAndSend(exchange.getName(), DELETE_ENTRY_ROUTING_KEY, entry);
            return true;
        } catch (AmqpException exception) {
            LOGGER.error("Error adding Entry to Delete Entry Exchange", exception);
        }
        return false;
    }

    /**
     * Adds the {@link T} to the update {@link Exchange}.
     *
     * @param entry - The {@link T} to be processed.
     * @param <T>   An {@link Object} to be added to the {@link Exchange}.
     */
    public <T> boolean updateEntry(T entry) {
        Exchange exchange = rabbitMQConfiguration.getEntryUpdateExchange();
        try {
            rabbitTemplate.convertAndSend(exchange.getName(), UPDATE_ENTRY_ROUTING_KEY, entry);
            return true;
        } catch (AmqpException exception) {
            LOGGER.error("Error adding Entry to Update Entry Exchange", exception);
        }
        return false;
    }
}
