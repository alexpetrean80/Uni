package group2.cms.controller;

import group2.cms.service.DTO.PresentationRequest;
import group2.cms.exceptions.BackendException;
import group2.cms.service.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PresentationController {

    @Autowired
    private PresentationService presentations;

    @PostMapping("presentations/add")
    public ResponseEntity<?> addPresentations(@RequestBody PresentationRequest presentationRequest){
        try{
            var addedPresentation = presentations.addPresentation(presentationRequest.getPresentationID(),
                    presentationRequest.getPaper(),
                    presentationRequest.getSection(),
                    presentationRequest.getStartDate(),
                    presentationRequest.getEndDate(),
                    presentationRequest.getPresentation());
            return new ResponseEntity<>(
                    addedPresentation,
                    HttpStatus.OK
            );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("presentations/delete")
    public ResponseEntity<?> deletePresentations(@RequestBody PresentationRequest presentationRequest){
        try{
            presentations.deletePresentation(presentationRequest.getPresentationID());
            return new ResponseEntity<>(
                    "Presentation deleted",
                    HttpStatus.OK
            );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("presentations/list")
    public ResponseEntity<?> getAllPresentations(){
        return new ResponseEntity<>(
                presentations.getAllPresentations(),
                HttpStatus.OK
        );
    }

    @GetMapping("presentations/{presentationID}")
    public ResponseEntity<?> getPresentation(@PathVariable Long presentationID){
        try{
            return new ResponseEntity<>(
                    presentations.getPresentationByID(presentationID),
                    HttpStatus.OK
            );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
