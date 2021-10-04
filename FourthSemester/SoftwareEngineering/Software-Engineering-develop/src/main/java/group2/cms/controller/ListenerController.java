package group2.cms.controller;

import group2.cms.service.DTO.ListenerRequest;
import group2.cms.exceptions.BackendException;
import group2.cms.service.ListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ListenerController {
    @Autowired
    private ListenerService listenerService;

    @PostMapping("listeners/add")
    public ResponseEntity<?> addListener(@RequestBody ListenerRequest request){
        try{
            var newListener = listenerService.addListener(
                    request.getListenerID()
            );
            return new ResponseEntity<>(
                    newListener,
                    HttpStatus.OK
            );
        } catch (BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("listeners/delete")
    public ResponseEntity<?> deleteListener(@RequestBody ListenerRequest request){
        try{
            listenerService.deleteListener(request.getListenerID());
            return new ResponseEntity<>(
                    "Listener deleted",
                    HttpStatus.OK
            );
        } catch (BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("listeners/list")
    public ResponseEntity<?> getListeners(){
        return new ResponseEntity<>(
                listenerService.getAllListeners(),
                HttpStatus.OK
        );
    }

    @GetMapping("listeners/list/{id}")
    public ResponseEntity<?> getListener(@PathVariable Long id){
        try{
            return new ResponseEntity<>(
                    listenerService.getListenerByID(id),
                    HttpStatus.OK
            );
        }catch (BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("listeners/addSection/{id}/{sectionID}")
    public ResponseEntity<?> addSection(@PathVariable Long id, @PathVariable Long sectionID){
        try{
            return new ResponseEntity<>(
                listenerService.addNewSection(id, sectionID),
                HttpStatus.OK
            );
        } catch (BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("listeners/listBySection/{sectionID}")
    public ResponseEntity<?> getListenersBySection(@PathVariable Long sectionID){
        try{
            return new ResponseEntity<>(
                    listenerService.getAllListenersBySection(sectionID),
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
