package group2.cms.controller;


import group2.cms.exceptions.BackendException;
import group2.cms.service.AuthorService;
import group2.cms.service.DTO.Author.AuthorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authors;

    @PostMapping("authors/add")
    public ResponseEntity<?> addAuthor(@RequestBody AuthorDTO authorDTO){
        try{
            var addedAuthor = authors.addAuthor(authorDTO);
            return new ResponseEntity<>(
                    addedAuthor,
                    HttpStatus.OK
            );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("authors/delete/{authorID}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long authorID){
        try{
            authors.deleteAuthor(authorID);
            return new ResponseEntity<>(
                    "Author deleted",
                    HttpStatus.OK
            );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("authors/list")
    public ResponseEntity<?> getAllAuthors(){
        return new ResponseEntity<>(
                authors.getAllAuthors(),
                HttpStatus.OK
        );
    }

    @GetMapping("authors/list/paper")
    public ResponseEntity<?> getAuthorsOfPaper(@RequestBody AuthorDTO authorDTO){
        try{
            return new ResponseEntity<>(
                    authors.getAuthorsOfPaper(authorDTO),
                    HttpStatus.OK
            ) ;
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

    }

    @GetMapping("authors/{authorID}")
    public ResponseEntity<?> getAuthor(@PathVariable Long authorID){
        try{
            return new ResponseEntity<>(
                    authors.getAuthorByID(authorID),
                    HttpStatus.OK
            );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("authors/email/{email}")
    public ResponseEntity<?> getAuthorByEmail(@PathVariable String email){
        try{
            return new ResponseEntity<>(
                    authors.getAuthorByEmail(email),
                    HttpStatus.OK
            );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("authors/username/{username}")
    public ResponseEntity<?> getAuthorByUsername(@PathVariable String username){
        try{
            return new ResponseEntity<>(
                    authors.getAuthorByUsername(username),
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
