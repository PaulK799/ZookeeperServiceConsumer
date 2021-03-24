package com.paulk.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * A {@link FeignClient} which interacts with the defined Restful API located on the configured Discovery Client.
 */
@FeignClient(name = "SpringBootTemplateProject", decode404 = true)
public interface EntryAPIClient {

    /**
     * A {@link PostMapping} API call for performing the Add Entry API call.
     *
     * @param actionInput - A generic {@link Object} of type {@link T} to be processed.
     * @param <T>         - An {@link Object} submitted as the request body.
     * @return A {@link ResponseEntity} with a response of type {@link Object}.
     */
    @PostMapping("/entries/entry")
    <T> ResponseEntity<Object> addEntry(@RequestBody T actionInput);

    /**
     * A {@link PostMapping} API call for performing the Delete Entry API call.
     *
     * @param actionInput - A generic {@link Object} of type {@link T} to be processed.
     * @param <T>         - An {@link Object} submitted as the request body.
     * @return A {@link ResponseEntity} with a response of type {@link Object}.
     */
    @DeleteMapping("/entries/entry")
    <T> ResponseEntity<Object> deleteEntry(@RequestBody T actionInput);

    /**
     * A {@link PostMapping} API call for performing the Update Entry API call.
     *
     * @param actionInput - A generic {@link Object} of type {@link T} to be processed.
     * @param <T>         - An {@link Object} submitted as the request body.
     * @return A {@link ResponseEntity} with a response of type {@link Object}.
     */
    @PutMapping("/entries/entry")
    <T> ResponseEntity<Object> updateEntry(@RequestBody T actionInput);

    /**
     * A {@link GetMapping} API call utilizing the Get Entry API Call.
     *
     * @param value - A {@link PathVariable} for the {@link String} value to be processed.
     * @param id    - A {@link PathVariable} for the {@link String} id to be processed.
     * @return A {@link ResponseEntity} with a response of type {@link Object}.
     */
    @GetMapping("/entries/entry/{value}/id/{id}")
    ResponseEntity<Object> getEntry(@PathVariable String value, @PathVariable String id);

    /**
     * A {@link GetMapping} API call utilizing the Get All Entries API Call.
     *
     * @return A {@link ResponseEntity} with a response of type {@link Object}.
     */
    @GetMapping("/entries")
    ResponseEntity<Object> getEntries();
}
