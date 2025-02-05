package com.example.application.service;

import com.example.application.model.Item;
import com.example.application.repository.ReceiptRepository;
import com.example.application.model.Receipt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Service
public class ReceiptService {

    private final ReceiptRepository receiptsRepository;

    public ReceiptService(ReceiptRepository receiptRepository){
        this.receiptsRepository=receiptRepository;
    }

    public String process(Receipt receipt){
        try {
            String uuid = String.valueOf(UUID.randomUUID());
            receiptsRepository.insertIntoDatabase(uuid, receipt);
            log.info("Generated Id: {}", uuid);
            return uuid;
        } catch(Exception e){
            log.error("Error processing Receipt: {}", e.getMessage());
            return null;
        }
    }

    public int getPointsById(String id){
        Receipt receipt= receiptsRepository.findReceiptById(id);
        if(receipt==null){
            log.error("ID not found");
            return -1;
        }
        else {
            int points =pointCalculator(receipt);
            log.info("points= {}", points);
            return points;
        }
    }


    private int pointCalculator(Receipt receipt){
        int points=0;

//        One point for every alphanumeric character in the retailer name.
        for(char c:receipt.getRetailer().toCharArray()){
            if(Character.isLetterOrDigit(c)){
                points++;
            }
        }
        log.debug("{} points for alphanumeric ",points);

//        50 points if the total is a round dollar amount with no cents.
        if(Double.parseDouble(receipt.getTotal()) % 1 ==0){
            points+=50;
            log.debug("50 points for round amount");
        }

//        25 points if the total is a multiple of 0.25.
        if(Double.parseDouble(receipt.getTotal()) % 0.25 ==0){
            points+=25;
            log.debug("25 points for total being multiple of 0.25");
        }

//        5 points for every two items on the receipt.
        int totalNumberOfItems=receipt.getItems().length;
        points+=(totalNumberOfItems/2) * 5;
        log.debug("{} points for every 2 items",totalNumberOfItems/2 * 5);

//        If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.
        for(Item item:receipt.getItems()){
            int descLength=item.getShortDescription().trim().length();
            if(descLength % 3 ==0){
                double price= Double.parseDouble(item.getPrice());
                points+=(int) Math.ceil(price *0.2);
                log.debug("{} points for item desc", (int) Math.ceil(price*0.2));
            }
        }

//        6 points if the day in the purchase date is odd.
        String[] dateOfPurchase=receipt.getPurchaseDate().split("-");
        int dayOfPurchase= Integer.parseInt(dateOfPurchase[2]);
        if(dayOfPurchase % 2==1){
            points+=6;
            log.debug("6 points for odd purchase day");
        }

//        10 points if the time of purchase is after 2:00pm and before 4:00pm
        LocalTime time = LocalTime.parse(receipt.getPurchaseTime(), DateTimeFormatter.ofPattern("HH:mm"));
        if(time.isAfter(LocalTime.of(14,0)) && time.isBefore(LocalTime.of(16,0))){
            points+=10;
            log.debug("10 points for time of purchase");
        }

        return points;
    }
}
