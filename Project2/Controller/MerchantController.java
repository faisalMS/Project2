package com.example.ecommercewebsite.controller;

import com.example.ecommercewebsite.modle.Api;
import com.example.ecommercewebsite.modle.Merchant;
import com.example.ecommercewebsite.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping
    public ResponseEntity<ArrayList<Merchant>> getMerchants(){
        return ResponseEntity.status(HttpStatus.OK).body(merchantService.getMerchants());
    }

    @PostMapping
    public ResponseEntity<Api> addMerchants(@RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isAddMerchants = merchantService.addMerchants(merchant);
        if (!isAddMerchants) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Error adding a merchant", 200));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("New merchant added", 200));
    }

    @DeleteMapping("/{merchantID}")
    public ResponseEntity<Api> deleteMerchants(@PathVariable String merchantID) {
        Boolean deleteMerchants = merchantService.deleteMerchants(merchantID);
        if (!deleteMerchants) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("merchantID doesn't exists!",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("merchantID deleted!",200));
    }

    @PutMapping
    public ResponseEntity<Api> updateMerchants(@RequestBody @Valid Merchant merchant, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        Boolean updateMerchants = merchantService.updateMerchants(merchant);
        if (!updateMerchants) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Advisor edited!",500));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("merchant doesn't exists!",200));
    }

}
