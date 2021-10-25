package com.example.client.controller;

import com.example.client.service.ClientService;
import com.example.client.service.dto.ClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    @Autowired
    private ClientService service;

    @PostMapping("/client")
    public ResponseEntity<?> createClient(@RequestBody ClientRequest req) {
        try {
            return new ResponseEntity<>(
                    service.addClient(req),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable String id) {
        try {
            service.deleteClient(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<?> updateClient(@PathVariable String id, @RequestBody ClientRequest req) {
        try {
            return new ResponseEntity<>(
                    service.updateClient(id, req),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<?> getClientById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(
                    service.getClientById(id),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/clients")
    public ResponseEntity<?> getAllClients() {
        return new ResponseEntity<>(
                service.getAllClients()
                        .getClients(),
                HttpStatus.OK
        );
    }

    @GetMapping("/clients/{address}")
    public ResponseEntity<?> getClientsByAddress(@PathVariable String address) {
        return new ResponseEntity<>(
                service.getClientsByAddress(address)
                        .getClients(),
                HttpStatus.OK
        );
    }
}
