package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.Service.PurchaseHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/purchaseHistories")
@RequiredArgsConstructor
public class PurchaseHistory {

    private final PurchaseHistoryService purchaseHistoryService;

    @GetMapping
    public ResponseEntity getPurchaseHistories(){
        return ResponseEntity.status(HttpStatus.OK).body(purchaseHistoryService.getPurchaseHistories());
    }
}
