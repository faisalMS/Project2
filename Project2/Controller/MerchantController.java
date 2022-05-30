package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.Model.Api;
import com.example.ecommercewebsite.Model.Merchant;
import com.example.ecommercewebsite.Service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/merchants")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;
    @GetMapping
    public ResponseEntity getMerchants(){
        return ResponseEntity.status(HttpStatus.OK).body(merchantService.getMerchants());
    }

    @PostMapping
    public ResponseEntity addMerchants(@RequestBody @Valid Merchant merchant, Errors error){
        if(error.hasFieldErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(message,400));
        }
        Boolean isMerchantAdded=merchantService.addMerchants(merchant);
        if(!isMerchantAdded){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Merchant not added",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Merchant added",200));
    }

    @PutMapping("/{merchantId}")
    public ResponseEntity updateMerchants(@RequestBody @Valid Merchant merchant , @PathVariable String merchantId, Errors error){
        if(error.hasFieldErrors()){
            String err_msg=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(err_msg,400));
        }
        Boolean isUpdateMerchant = merchantService.updateMerchants(merchant, merchantId);
        if(!isUpdateMerchant){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("No updated",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Merchant updated",200));

    }

    @DeleteMapping("/{merchantId}")
    public ResponseEntity deleteMerchants(@PathVariable String merchantId){
        Boolean isMerchantDeleted=merchantService.deleteMerchants(merchantId);
        if(!isMerchantDeleted){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("No deleted",400));

        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Merchant deleted",200));

    }
}

