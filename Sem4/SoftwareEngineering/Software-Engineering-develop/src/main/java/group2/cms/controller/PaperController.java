package group2.cms.controller;

import group2.cms.exceptions.BackendException;
import group2.cms.service.DTO.Paper.PaperDTO;
import group2.cms.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaperController {

    @Autowired
    private PaperService paperService;

    @PostMapping("/paper")
    public ResponseEntity<?> addPaper(@RequestBody PaperDTO paperDTO){
        try{
            return new ResponseEntity<>(
                    paperService.addPaper(paperDTO),
                    HttpStatus.OK
            );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("/paper/{paperId}")
    public ResponseEntity<?> deletePaper(@PathVariable Long paperId){
        try{
            paperService.deletePaper(paperId);
            return new ResponseEntity<>(
                    "Paper deleted",
                    HttpStatus.OK
            );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }


    @GetMapping("/paper")
    public ResponseEntity<?> getAllPapers(){
        return new ResponseEntity<>(
                paperService.getAllPapers(),
                HttpStatus.OK
        );
    }

    @GetMapping("/paper/{paperId}")
    public ResponseEntity<?> getPaper(@PathVariable Long paperId){
        try{
            return new ResponseEntity<>(
                    paperService.getPaperById(paperId),
                    HttpStatus.OK
            );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/paper/title/{title}")
    public ResponseEntity<?> getPaperByTitle(@PathVariable String title){
        try{
            return new ResponseEntity<>(
                    paperService.getPaperByTitle(title),
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
