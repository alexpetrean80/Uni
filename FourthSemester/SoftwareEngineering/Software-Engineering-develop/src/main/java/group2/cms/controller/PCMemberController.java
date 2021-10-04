package group2.cms.controller;

import group2.cms.service.DTO.PCMemberRequest;
import group2.cms.exceptions.BackendException;
import group2.cms.service.PCMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PCMemberController {

    @Autowired
    private PCMemberService pcMembers;

    @PostMapping("pcmembers/add")
    public ResponseEntity<?> addPCMember(@RequestBody PCMemberRequest request){
        try{
            var newPCMember = pcMembers.addPCMember(
                    request.getUserID(),
                    request.getAffiliation(),
                    request.getWebsite());
            return new ResponseEntity<>(
                    newPCMember,
                    HttpStatus.OK
            );
        }catch (BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PostMapping("pcmembers/update")
    public ResponseEntity<?> updatePCMember(@RequestBody PCMemberRequest request){
        try{
            var affiliation = request.getAffiliation();;
            var website = request.getWebsite();
            var updatedPCMember = pcMembers.updatePCMember(
                    request.getUserID(),
                    affiliation.isBlank()? Optional.empty() : Optional.of(affiliation),
                    website.isBlank()? Optional.empty() : Optional.of(website)
            );
            return new ResponseEntity<>(
                    updatedPCMember,
                    HttpStatus.OK
            );
        }catch (BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("pcmembers/delete")
    public ResponseEntity<?> deletePCMember(@RequestBody PCMemberRequest request){
        try{
           pcMembers.deletePCMember(request.getUserID());
           return new ResponseEntity<>(
                   "PCMember deleted",
                   HttpStatus.OK
           );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("pcmembers/list")
    public ResponseEntity<?> getPCMembers(){
        return new ResponseEntity<>(
                pcMembers.getAllPCMembers(),
                HttpStatus.OK
        );
    }

    @GetMapping("pcmembers/list/affiliation/{affiliation}")
    public ResponseEntity<?> getPCMembersOfAffiliation(@PathVariable String affiliation){
        return new ResponseEntity<>(
                pcMembers.getPCMembersByAffiliation(affiliation),
                HttpStatus.OK
        );
    }

    @GetMapping("pcmembers/list/{id}")
    public ResponseEntity<?> getPCMember(@PathVariable Long id){
        try{
            return new ResponseEntity<>(
                    pcMembers.getPCMemberByID(id),
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
