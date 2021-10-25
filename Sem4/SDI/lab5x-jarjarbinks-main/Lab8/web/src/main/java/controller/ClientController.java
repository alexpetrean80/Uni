package controller;

import converter.ClientConverter;
import dto.ClientDto;
import dto.CollectionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.interfaces.ClientService;

import java.util.stream.Collectors;

@RestController
public class ClientController {
    public static Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService service;

    @Autowired
    private ClientConverter converter;

    @RequestMapping(value = "/client", method = RequestMethod.POST)
    ResponseEntity<?> addClient(@RequestBody ClientDto clientDto) {
        logger.info("Adding client.");
        service.addClient(clientDto.getName(), clientDto.getAddress(), clientDto.getPhoneNumber());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/client/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody ClientDto clientDto) {
        logger.info("Updating client.");
        service.updateClient(id, clientDto.getName(), clientDto.getAddress(), clientDto.getPhoneNumber());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/client/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Long id) {
        logger.info("Deleting client.");
        service.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    CollectionDto<Long, ClientDto> getClients() {
        logger.info("Requesting clients.");
        return new CollectionDto<>(service.getAllClients()
                .stream()
                .map(client -> converter.toDto(client))
                .collect(Collectors.toList()));
    }

    @RequestMapping(value = "/clients/{name}", method = RequestMethod.GET)
    CollectionDto<Long, ClientDto> getClientsByName(@PathVariable String name) {
        logger.trace("Requesting clients.");
        logger.info("Requesting clients.");
        return new CollectionDto<>(service.filterClientsByName(name)
                .stream()
                .map(client -> converter.toDto(client))
                .collect(Collectors.toList()));
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    CollectionDto<Long, ClientDto> getClientsById(@PathVariable Long id) {
        logger.info("Requesting clients.");
        logger.trace("Requesting clients.");
        return new CollectionDto<>(service.filterClientsById(id)
                .stream()
                .map(client -> converter.toDto(client))
                .collect(Collectors.toList()));
    }

    @RequestMapping(value = "/clients/{addr}", method = RequestMethod.GET)
    CollectionDto<Long, ClientDto> getClientsByAddress(@PathVariable String addr) {
        logger.info("Requesting clients.");
        logger.trace("Requesting clients.");
        return new CollectionDto<>(service.filterClientsByAddress(addr)
                .stream()
                .map(client -> converter.toDto(client))
                .collect(Collectors.toList()));
    }
}
