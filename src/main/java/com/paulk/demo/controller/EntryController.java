package com.paulk.demo.controller;

import com.paulk.demo.client.SpringBootTemplateProjectClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * A {@link Controller} which utilizes the {@link SpringBootTemplateProjectClient}.
 */
@Controller
public class EntryController {

    @Autowired
    protected SpringBootTemplateProjectClient springBootTemplateProjectClient;

    /**
     * A {@link PostMapping} API call utilizing the {@link SpringBootTemplateProjectClient#addEntry(Object)}.
     *
     * @param actionInput - A generic {@link Object} of type {@link T} to be processed.
     * @param <T>         - An {@link Object} submitted as the request body.
     * @return A {@link ResponseEntity} with a response of type {@link Object}.
     */
    @PostMapping("/entries/entry")
    public <T> ResponseEntity<Object> addEntry(@RequestBody T actionInput) {
        return springBootTemplateProjectClient.addEntry(actionInput);
    }

    /**
     * A {@link DeleteMapping} API call utilizing the {@link SpringBootTemplateProjectClient#deleteEntry(Object)}.
     *
     * @param actionInput - A generic {@link Object} of type {@link T} to be processed.
     * @param <T>         - An {@link Object} submitted as the request body.
     * @return A {@link ResponseEntity} with a response of type {@link Object}.
     */
    @DeleteMapping("/entries/entry")
    public <T> ResponseEntity<Object> deleteEntry(@RequestBody T actionInput) {
        return springBootTemplateProjectClient.deleteEntry(actionInput);
    }

    /**
     * A {@link PutMapping} API call utilizing the {@link SpringBootTemplateProjectClient#updateEntry(Object)}.
     *
     * @param actionInput - A generic {@link Object} of type {@link T} to be processed.
     * @param <T>         - An {@link Object} submitted as the request body.
     * @return A {@link ResponseEntity} with a response of type {@link Object}.
     */
    @PutMapping("/entries/entry")
    public <T> ResponseEntity<Object> updateEntry(@RequestBody T actionInput) {
        return springBootTemplateProjectClient.updateEntry(actionInput);
    }

    /**
     * A {@link GetMapping} API call utilizing the {@link SpringBootTemplateProjectClient#getEntry(String, String)}.
     *
     * @param value - A {@link PathVariable} for the {@link String} value to be processed.
     * @param id    - A {@link PathVariable} for the {@link String} id to be processed.
     * @return A {@link ResponseEntity} with a response of type {@link Object}.
     */
    @GetMapping("/entries/entry/{value}/id/{id}")
    public ResponseEntity<Object> getEntry(@PathVariable String value, @PathVariable String id) {
        return springBootTemplateProjectClient.getEntry(value, id);
    }

    /**
     * A {@link GetMapping} API call utilizing the {@link SpringBootTemplateProjectClient#getEntries()}.
     *
     * @return A {@link ResponseEntity} with a response of type {@link Object}.
     */
    @GetMapping("/entries")
    public ResponseEntity<Object> getEntries() {
        return springBootTemplateProjectClient.getEntries();
    }
}
