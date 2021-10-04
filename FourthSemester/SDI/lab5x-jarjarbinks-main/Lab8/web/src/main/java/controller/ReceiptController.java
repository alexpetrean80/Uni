package controller;

import converter.ReceiptConverter;
import dto.CollectionDto;
import dto.ReceiptDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.interfaces.ReceiptService;

import java.util.stream.Collectors;

@RestController
public class ReceiptController {
    public static Logger logger = LoggerFactory.getLogger(ReceiptController.class);

    @Autowired
    ReceiptService service;

    @Autowired
    ReceiptConverter converter;

    @RequestMapping(value = "/receipt", method = RequestMethod.POST)
    ResponseEntity<?> addReceipt(@RequestBody ReceiptDto receipt) {
        service.addReceipt(receipt.getPurchaseID(), receipt.getTotalPrice());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/receipts", method = RequestMethod.GET)
    CollectionDto<Long, ReceiptDto> getAllReceipts() {
        return new CollectionDto<>(service.getAllReceipts()
                .stream()
                .map(receipt -> converter.toDto(receipt))
                .collect(Collectors.toList()));
    }
}
