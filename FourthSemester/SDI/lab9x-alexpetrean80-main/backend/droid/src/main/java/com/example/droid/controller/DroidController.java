package com.example.droid.controller;

import com.example.droid.service.DroidService;
import com.example.droid.service.dto.DroidRequest;
import com.example.droid.service.dto.DroidResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DroidController {
    @Autowired
    private DroidService service;

    @PostMapping("/droid")
    private ResponseEntity<?> createDroid(@RequestBody DroidRequest req) {
        try {
            return new ResponseEntity<>(
                    service.addDroid(req),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("/droid/{id}")
    private ResponseEntity<?> deleteDroid(@PathVariable String id) {
        try {
            service.deleteDroid(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping("/droid/{id}")
    private ResponseEntity<?> updateDroid(@PathVariable String id, @RequestBody DroidRequest req) {
        try {
            return new ResponseEntity<>(
                    service.updateDroid(id, req),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/droid/{id}")
    private ResponseEntity<?> getDroidById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(
                    service.getDroidById(id),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/droids")
    private ResponseEntity<?> getDroids() {
        return new ResponseEntity<>(
                service.getAllDroids()
                        .getDroids(),
                HttpStatus.OK
        );
    }

    @GetMapping("/droids/{model}")
    private ResponseEntity<?> getDroidsByModel(@PathVariable String model) {
        return new ResponseEntity<>(
                service.getDroidsByModel(model)
                        .getDroids(),
                HttpStatus.OK
        );
    }
}
