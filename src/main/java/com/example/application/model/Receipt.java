package com.example.application.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Receipt {

    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
    private Item[] items;
    private String total;


    public String getRetailer() {
        return retailer;
    }


    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public Item[] getItems() {
        return items;
    }

    public String getTotal() {
        return total;
    }

}
