package com.paulk.demo.controller;

import com.paulk.demo.client.SpringBootTemplateProjectClient;
import com.paulk.demo.service.EntryQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * A {@link Controller} which utilizes the {@link SpringBootTemplateProjectClient}.
 */
@Controller
public class EntryQueueController {

    @Autowired
    protected EntryQueueService entryQueueService;

    /**
     * A {@link PostMapping} API call utilizing the {@link EntryQueueService#addEntry(Object)}.
     *
     * @param actionInput - A generic {@link Object} of type {@link T} to be processed.
     * @param <T>         - An {@link Object} submitted as the request body.
     * @return A {@link ResponseEntity} with a response of type {@link Object}.
     */
    @PostMapping("/queue/entries/entry")
    public <T> ResponseEntity<Object> addEntry(@RequestBody T actionInput) {
        boolean isSuccessfulOperation = entryQueueService.addEntry(actionInput);
        if (isSuccessfulOperation) {
            return new ResponseEntity<>("Entry has been added to the add queue successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Entry could not be added to the delete queue.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * A {@link DeleteMapping} API call utilizing the {@link SpringBootTemplateProjectClient#deleteEntry(Object)}.
     *
     * @param actionInput - A generic {@link Object} of type {@link T} to be processed.
     * @param <T>         - An {@link Object} submitted as the request body.
     * @return A {@link ResponseEntity} with a response of type {@link Object}.
     */
    @DeleteMapping("/queue/entries/entry")
    public <T> ResponseEntity<Object> deleteEntry(@RequestBody T actionInput) {
        boolean isSuccessfulOperation = entryQueueService.deleteEntry(actionInput);
        if (isSuccessfulOperation) {
            return new ResponseEntity<>("Entry has been added to the delete queue successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Entry could not be added to the delete queue.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * A {@link PutMapping} API call utilizing the {@link SpringBootTemplateProjectClient#updateEntry(Object)}.
     *
     * @param actionInput - A generic {@link Object} of type {@link T} to be processed.
     * @param <T>         - An {@link Object} submitted as the request body.
     * @return A {@link ResponseEntity} with a response of type {@link Object}.
     */
    @PutMapping("/queue/entries/entry")
    public <T> ResponseEntity<Object> updateEntry(@RequestBody T actionInput) {
        boolean isSuccessfulOperation = entryQueueService.updateEntry(actionInput);
        if (isSuccessfulOperation) {
            return new ResponseEntity<>("Entry has been added to the update queue successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Entry could not be added to the update queue.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
