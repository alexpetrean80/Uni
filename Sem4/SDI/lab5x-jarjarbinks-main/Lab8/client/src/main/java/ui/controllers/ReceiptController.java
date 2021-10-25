package ui.controllers;


import domain.baseEntities.Client;
import domain.baseEntities.Receipt;
import dto.ClientDto;
import dto.CollectionDto;
import dto.ReceiptDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class ReceiptController {

    @Autowired
    ExecutorService executorService;

    @Autowired
    RestTemplate template;

    public CompletableFuture<String> addReceipt(Long purchaseId, double totalPrice){
        return CompletableFuture.supplyAsync(()->{
            var dto = new ReceiptDto(purchaseId,totalPrice);
            template.postForObject("http://localhost:4000/receipt",dto,ReceiptDto.class);
            return "Receipt added.";
        },executorService);
    }

    public CompletableFuture<Iterable<Receipt>> getAllReceipts(){
        return CompletableFuture.supplyAsync(() -> {
            CollectionDto<Long, ReceiptDto> dto = template.getForObject("http://localhost:4000/clients", CollectionDto.class);
            return dto.getElements().stream().map(d -> {
                var receipt = new Receipt(d.getPurchaseID(), d.getPurchaseID(), d.getTotalPrice());
                receipt.setId(d.getId());
                return receipt;
            }).collect(Collectors.toList());
        }, executorService);
    }
}
