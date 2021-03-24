package com.paulk.demo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Interacts with the {@link EntryAPIClient} which is a {@link FeignClient}.
 */
@Configuration
@EnableFeignClients
@EnableDiscoveryClient
public class SpringBootTemplateProjectClient {

    @Autowired
    protected EntryAPIClient entryAPIClient;

    /**
     * An API call utilizing the {@link EntryAPIClient#addEntry(T)}.
     *
     * @param actionInput - A generic {@link T} of type {@link T} to be processed.
     * @param <T>         - An {@link T} submitted as the request body.
     * @return A {@link ResponseEntity} with a response of type {@link T}.
     */
    public <T> ResponseEntity<Object> addEntry(T actionInput) {
        return entryAPIClient.addEntry(actionInput);
    }

    /**
     * An API call utilizing the {@link EntryAPIClient#addEntry(T)}.
     *
     * @param actionInput - A generic {@link T} of type {@link T} to be processed.
     * @param <T>         - An {@link T} submitted as the request body.
     * @return A {@link ResponseEntity} with a response of type {@link T}.
     */
    public <T> ResponseEntity<Object> deleteEntry(T actionInput) {
        return entryAPIClient.deleteEntry(actionInput);
    }

    /**
     * An API call utilizing the {@link EntryAPIClient#addEntry(T)}.
     *
     * @param actionInput - A generic {@link T} of type {@link T} to be processed.
     * @param <T>         - An {@link T} submitted as the request body.
     * @return A {@link ResponseEntity} with a response of type {@link T}.
     */
    public <T> ResponseEntity<Object> updateEntry(T actionInput) {
        return entryAPIClient.updateEntry(actionInput);
    }

    /**
     * An API call utilizing the {@link EntryAPIClient#getEntry(String, String)}.
     *
     * @param value - A {@link PathVariable} for the {@link String} value to be processed.
     * @param id    - A {@link PathVariable} for the {@link String} id to be processed.
     * @return A {@link ResponseEntity} with a response of type {@link Object}.
     */
    public ResponseEntity<Object> getEntry(String value, String id) {
        return entryAPIClient.getEntry(value, id);
    }

    /**
     * An API call utilizing the {@link EntryAPIClient#getEntries()}.
     *
     * @return A {@link ResponseEntity} with a response of type {@link Object}.
     */
    public ResponseEntity<Object> getEntries() {
        return entryAPIClient.getEntries();
    }
}
