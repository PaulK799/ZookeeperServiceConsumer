package com.paulk.demo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

@Configuration
@EnableFeignClients
@EnableDiscoveryClient
public class SpringBootTemplateProjectClient {

    @Autowired
    protected EntryAPIClient entryAPIClient;

    public <T> ResponseEntity<Object> addEntry(T actionInput) {
        return entryAPIClient.addEntry(actionInput);
    }

    public <T> ResponseEntity<Object> deleteEntry(T actionInput) {
        return entryAPIClient.deleteEntry(actionInput);
    }

    public <T> ResponseEntity<Object> updateEntry(T actionInput) {
        return entryAPIClient.updateEntry(actionInput);
    }

    public ResponseEntity<Object> getEntry(String value, String id) {
        return entryAPIClient.getEntry(value, id);
    }

    public ResponseEntity<Object> getEntries() {
        return entryAPIClient.getEntries();
    }
}
