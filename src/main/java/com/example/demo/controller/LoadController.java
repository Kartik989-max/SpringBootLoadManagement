package com.example.demo.controller;

import com.example.demo.model.Load;
import com.example.demo.repository.LoadRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/load")
public class LoadController {

    private final LoadRepository loadRepository;

    public LoadController(LoadRepository loadRepository) {
        this.loadRepository = loadRepository;
    }

    @PostMapping
    public ResponseEntity<Load> createLoad(@RequestBody Load load) {
        load.setLoadId(null);  // Ensure Hibernate generates UUID
        Load savedLoad = loadRepository.save(load);
        return ResponseEntity.status(201).body(savedLoad);
    }

    @GetMapping
    public ResponseEntity<List<Load>> getAllLoads() {
        List<Load> loads = loadRepository.findAll();
        return ResponseEntity.ok(loads);
    }

    @GetMapping("/{loadId}")
    public ResponseEntity<Load> getLoadById(@PathVariable UUID loadId) {
        Optional<Load> load = loadRepository.findById(loadId);
        return load.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{loadId}")
    public ResponseEntity<Load> updateLoad(@PathVariable UUID loadId, @RequestBody Load updatedLoad) {
        return loadRepository.findById(loadId).map(existingLoad -> {
            updatedLoad.setLoadId(loadId);  // Ensure ID remains the same
            updatedLoad.setVersion(existingLoad.getVersion()); // Maintain version consistency
            Load savedLoad = loadRepository.save(updatedLoad);
            return ResponseEntity.ok(savedLoad);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{loadId}")
    public ResponseEntity<Void> deleteLoad(@PathVariable UUID loadId) {
        if (loadRepository.existsById(loadId)) {
            loadRepository.deleteById(loadId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
