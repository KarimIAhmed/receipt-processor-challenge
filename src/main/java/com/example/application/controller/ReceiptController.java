package com.example.application.controller;

import com.example.application.model.Id;
import com.example.application.model.Points;
import com.example.application.service.ReceiptService;
import com.example.application.model.Receipt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService){
        this.receiptService=receiptService;
    }

    @PostMapping("/process")
    public ResponseEntity<Id> process(@RequestBody Receipt receipt){
        String id=receiptService.process(receipt);
        if(id==null || id.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(new Id(id), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<Points> getPointsById(@PathVariable String id){
        int points=receiptService.getPointsById(id);
        if(points==-1){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(new Points(points),HttpStatus.OK);
        }
    }
}
