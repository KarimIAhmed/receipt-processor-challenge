package com.example.application.repository;

import com.example.application.model.Receipt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
@Slf4j
public class ReceiptRepository {

    HashMap<String, Receipt> receiptDatabase=new HashMap<>();

    public void insertIntoDatabase(String id,Receipt receipt){
        receiptDatabase.put(id,receipt);
        log.debug("database= {}", receiptDatabase);
    }
    public Receipt findReceiptById(String id){
        return receiptDatabase.get(id);
    }
}