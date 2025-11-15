package com.example.opdracht_frameworks_spring.controller;

import com.example.opdracht_frameworks_spring.dao.VlogDAO;
import com.example.opdracht_frameworks_spring.data.entity.Vlog;
import com.example.opdracht_frameworks_spring.dto.VlogPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/vlogs")
public class VlogController {
    private final VlogDAO dao;

    public VlogController(VlogDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Vlog> getVlogs() {
        return dao.findAll();
    }

    @GetMapping("{id}")
    public Vlog getVlogById(@PathVariable("id") Integer id) {
        return dao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vlog not found"));
    }

    @PostMapping
    public ResponseEntity<Void> createVlog(@RequestBody VlogPayload payload) {
        Vlog vlog = dao.save(payload);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vlog.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateVlog(@PathVariable("id") Integer id, @RequestBody VlogPayload payload) {
        return dao.update(id, payload)
                .map(updatedVlog -> new ResponseEntity<>("", HttpStatus.NO_CONTENT))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vlog not found"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVlog(@PathVariable("id") Integer id) {
        if (dao.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vlog not found");
        }
        dao.deleteById(id);
    }
}
