package com.paulk.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "SpringBootTemplateProject", decode404 = true)
public interface EntryAPIClient {

    @PostMapping("/entries/entry")
    <T> ResponseEntity<Object> addEntry(@RequestBody T actionInput);

    @DeleteMapping("/entries/entry")
    <T> ResponseEntity<Object> deleteEntry(@RequestBody T actionInput);

    @PutMapping("/entries/entry")
    <T> ResponseEntity<Object> updateEntry(@RequestBody T actionInput);

    @GetMapping("/entries/entry/{value}/id/{id}")
    ResponseEntity<Object> getEntry(@PathVariable String value, @PathVariable String id);

    @GetMapping("/entries")
    ResponseEntity<Object> getEntries();
}
