package com.example.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receipts")
public class ReceiptsController {


    @PostMapping("/process")
    public ResponseEntity<Object> response(){

        return null;
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<Object> getPointsById(){

        return null;
    }
}
