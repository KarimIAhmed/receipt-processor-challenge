package com.example.application.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String shortDescription;
    private String price;

    public String getShortDescription() {
        return shortDescription;
    }

    public String getPrice() {
        return price;
    }

}
