package controller;

import converter.DroidConverter;
import dto.CollectionDto;
import dto.DroidDto;
import exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.interfaces.DroidService;

import java.util.stream.Collectors;

@RestController
public class DroidController {
    public static final Logger logger = LoggerFactory.getLogger(DroidController.class);

    @Autowired
    private DroidService service;

    @Autowired
    private DroidConverter converter;

    @RequestMapping(value = "/droids", method = RequestMethod.GET)
    CollectionDto<Long, DroidDto> getAllDroids() {
        logger.info("Requesting droids..");
        return new CollectionDto<>(service.getAllDroids()
                .stream()
                .map((droid -> converter.toDto(droid)))
                .collect(Collectors.toList()));
    }

    @RequestMapping(value = "/droid", method = RequestMethod.POST)
    ResponseEntity<?> addDroid(@RequestBody DroidDto droidDto) {
        logger.info("Adding droid.");
        logger.trace("Adding droid.");
        service.addDroid(
                droidDto.getPowerUsage(),
                droidDto.getPrice(),
                droidDto.getBatteryTime(),
                droidDto.getModel(),
                droidDto.isDriver());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/droid/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> updateDroid(@PathVariable Long id, @RequestBody DroidDto dto) {
        try {
            service.updateDroid(id,
                    dto.getPowerUsage(),
                    dto.getPrice(),
                    dto.getBatteryTime(),
                    dto.getModel(),
                    dto.isDriver());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/droid/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteDroid(@PathVariable Long id) {
        try {
            service.deleteDroid(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/droids/{power}", method = RequestMethod.GET)
    CollectionDto<Long, DroidDto> getDroidsByMinimumPowerUsage(@PathVariable int power) {
        return new CollectionDto<>(service.getDroidsByMinimumPowerUsage(power)
                .stream()
                .map((droid) -> converter.toDto(droid))
                .collect(Collectors.toList()));
    }

    @RequestMapping(value = "/droids/{id}", method = RequestMethod.GET)
    CollectionDto<Long, DroidDto> getDroidsById(@PathVariable Long id) {
        return new CollectionDto<>(service.getDroidsById(id)
                .stream()
                .map((droid) -> converter.toDto(droid))
                .collect(Collectors.toList()));
    }

    @RequestMapping(value = "/droids/{model}", method = RequestMethod.GET)
    CollectionDto<Long, DroidDto> getDroidsByModel(@PathVariable String model) {
        return new CollectionDto<>(service.getDroidsByModel(model)
                .stream()
                .map((droid) -> converter.toDto(droid))
                .collect(Collectors.toList()));
    }

}
