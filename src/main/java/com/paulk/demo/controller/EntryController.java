package com.paulk.demo.controller;

import com.paulk.demo.client.SpringBootTemplateProjectClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class EntryController {

    @Autowired
    protected SpringBootTemplateProjectClient springBootTemplateProjectClient;

    @PostMapping("/entries/entry")
    public <T> ResponseEntity<Object> addEntry(@RequestBody T actionInput, Model model) {
        return springBootTemplateProjectClient.addEntry(actionInput);
    }

    @DeleteMapping("/entries/entry")
    public <T> ResponseEntity<Object> deleteEntry(@RequestBody T actionInput, Model model) {
        return springBootTemplateProjectClient.deleteEntry(actionInput);
    }

    @PutMapping("/entries/entry")
    public <T> ResponseEntity<Object> updateEntry(@RequestBody T actionInput, Model model) {
        return springBootTemplateProjectClient.updateEntry(actionInput);
    }

    @GetMapping("/entries/entry/{value}/id/{id}")
    public ResponseEntity<Object> getEntry(@PathVariable String value, @PathVariable String id, Model model) {
        return springBootTemplateProjectClient.getEntry(value, id);
    }

    @GetMapping("/entries")
    public ResponseEntity<Object> getEntries(Model model) {
        return springBootTemplateProjectClient.getEntries();
    }
}
