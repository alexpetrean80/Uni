package group2.cms.controller;

import group2.cms.exceptions.BackendException;
import group2.cms.service.CMSUserService;
import group2.cms.service.DTO.CMSUser.CMSUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CMSUserController {

    @Autowired
    private CMSUserService users;

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody CMSUserDTO userDTO) {
        try {
            return new ResponseEntity<>(
                    users.addUser(userDTO),
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody CMSUserDTO userDTO) {
        try {
            return new ResponseEntity<>(
                    users.updateUser(userDTO),
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("/user/{userID}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userID){
        try{
            users.deleteUser(userID);
            return new ResponseEntity<>(
                    "User with ID: " + userID + "deleted.",
                    HttpStatus.OK
            );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        try{
            return new ResponseEntity<>(
                    users.getAllUsers(),
                    HttpStatus.OK
            );
        }catch (BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/user/{userID}")
    public ResponseEntity<?> getUserByID(@PathVariable Long userID){
        try{
            return new ResponseEntity<>(
                    users.getUserByID(userID),
                    HttpStatus.OK
            );
        }catch (BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/user/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        try{
            return new ResponseEntity<>(
                    users.getUserByEmail(email),
                    HttpStatus.OK
            );
        }catch (BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/user/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username){
        try{
            return new ResponseEntity<>(
                    users.getUserByUsername(username),
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
