package group2.cms.controller;

import group2.cms.exceptions.InvalidIDException;
import group2.cms.service.DTO.Section.SectionDTO;
import group2.cms.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SectionController {
    @Autowired
    private SectionService service;

    @PostMapping("/section")
    public ResponseEntity<?> addSection(@RequestBody SectionDTO request) {
        try {
            return new ResponseEntity<>(
                    service.addSection(request),
                    HttpStatus.CREATED
            );
        } catch (InvalidIDException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("/section/{id}")
    public ResponseEntity<?> deleteSection(@PathVariable Long id) {
        try {
            service.deleteSection(id);
            return new ResponseEntity<>(
                    "Section deleted.",
                    HttpStatus.OK
            );
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/sections")
    public ResponseEntity<?> getAllSections() {
        return new ResponseEntity<>(
                service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/section/{id}")
    public ResponseEntity<?> getSectionById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(
                    service.findById(id),
                    HttpStatus.OK
            );
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
