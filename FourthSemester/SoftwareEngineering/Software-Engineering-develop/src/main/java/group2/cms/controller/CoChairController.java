package group2.cms.controller;

import group2.cms.service.DTO.CoChairRequest;
import group2.cms.exceptions.BackendException;
import group2.cms.service.CoChairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CoChairController {
    @Autowired
    private CoChairService coChairService;

    @PostMapping("coChairs/add")
    public ResponseEntity<?> addCoChair(@RequestBody CoChairRequest request){
        try{
            var newCoChair = coChairService.addCoChair(
                    request.getUserID(),
                    request.getAffiliation(),
                    request.getWebsite()
            );
            return new ResponseEntity<>(
                    newCoChair,
                    HttpStatus.OK
            );
        } catch (BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PostMapping("coChairs/update")
    public ResponseEntity<?> updateCoChair(@RequestBody CoChairRequest request) {
        try {
            var affiliation = request.getAffiliation();
            var website = request.getWebsite();
            var updateCoChair = coChairService.updateCoChair(
                    request.getUserID(),
                    affiliation.isBlank() ? Optional.empty() : Optional.of(affiliation),
                    website.isBlank() ? Optional.empty() : Optional.of(website)
            );
            return new ResponseEntity<>(
                    updateCoChair,
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("coChairs/delete")
    public ResponseEntity<?> deleteCoChair(@RequestBody CoChairRequest request){
        try{
            coChairService.deleteCoChair(request.getUserID());
            return new ResponseEntity<>(
                    "CoChair deleted",
                    HttpStatus.OK
            );
        } catch (BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("coChairs/list")
    public ResponseEntity<?> getCoChairs(){
        return new ResponseEntity<>(
                coChairService.getAllCoChairs(),
                HttpStatus.OK
        );
    }

    @GetMapping("coChairs/list/{id}")
    public ResponseEntity<?> getCoChair(@PathVariable Long id){
        try{
            return new ResponseEntity<>(
                    coChairService.getCoChairById(id),
                    HttpStatus.OK
            );
        }catch (BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
