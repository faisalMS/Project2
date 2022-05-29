package com.example.ecommercewebsite.controller;

import com.example.ecommercewebsite.modle.Api;
import com.example.ecommercewebsite.modle.MerchantStock;
import com.example.ecommercewebsite.service.MerchantStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/merchantStock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    @GetMapping
    public ResponseEntity<ArrayList<MerchantStock>> getMerchantStocks(){
        return ResponseEntity.status(HttpStatus.OK).body(merchantStockService.getMerchantStocks());
    }
    @PostMapping
    public ResponseEntity<Api> addMerchantStocks(@RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isAddMerchantStocks = merchantStockService.addMerchantStocks(merchantStock);
        if (!isAddMerchantStocks) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Error adding a merchantStock", 200));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("New merchantStock added", 200));
    }

    @DeleteMapping("/{merchantStockID}")
    public ResponseEntity<Api> deleteMerchantStocks(@PathVariable String merchantStockID) {
        Boolean deleteMerchantStocks = merchantStockService.deleteMerchantStocks(merchantStockID);
        if (!deleteMerchantStocks) {
            return ResponseEntity.status(400).body(new Api("merchantStockID doesn't exists!",400));
        }
        return ResponseEntity.status(200).body(new Api("merchantStockID deleted!",200));
    }

    @PutMapping
    public ResponseEntity<Api> updateMerchantStocks(@RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        Boolean updateMerchantStocks = merchantStockService.updateMerchantStocks(merchantStock);
        if (!updateMerchantStocks) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Advisor edited!",500));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("advisorID doesn't exists!",200));
    }

    @PostMapping ("/addPToMS/{userId}/{merchantId}/{stock}")
    public ResponseEntity<Api> addPToMS(@PathVariable String userId, @PathVariable String merchantId, @PathVariable Integer stock) {
        Integer addPToMS = merchantStockService.addPToMS(userId, merchantId, stock);
        switch (addPToMS){
            case -1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Merchant not found",400));
            case 0:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Merchant doesn't sell this product",400));
            case 1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Out of stock",400));
            case 2:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("User doesn't have enough balance",400));
            case 3:
                return ResponseEntity.status(HttpStatus.OK).body(new Api("Purchase completed",200));
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Server error",500));
        }
    }
}
