package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite2.Model.Api;
import com.example.ecommercewebsite2.Model.MerchantStock;
import com.example.ecommercewebsite2.Service.MerchantStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/merchantStock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    @GetMapping
    public ResponseEntity getMerchantStocks(){
        return ResponseEntity.status(HttpStatus.OK).body(merchantStockService.getMerchantStocks());
    }

    @PostMapping
    public ResponseEntity addMerchant(@RequestBody @Valid MerchantStock merchant, Errors error){
        if(error.hasFieldErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(message,400));
        }
        Boolean isAddMerchant=merchantStockService.addMerchantStocks(merchant);
        if(!isAddMerchant){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("No added",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("MerchantStock added",200));
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid String merchId, String productId, Integer merchantStock , Errors errors){
        if(errors.hasFieldErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(message, 400));
        }
        Integer addMerchantStock = merchantStockService.addProductStocks(merchId, productId, merchantStock);
        switch (addMerchantStock){
            case -1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Invalid cart id",400));
            case 0:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Invalid user id",400));
            case 1 :
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Invalid product id",400));
            case 2:
                return ResponseEntity.status(HttpStatus.OK).body(new Api("Stock added",200));
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Server Error",500));

        }
    }

    @PutMapping("/update/{merchantId}")
    public ResponseEntity updateMerchantStock(@RequestBody @Valid MerchantStock merchant , @PathVariable String merchantId, Errors error){
        if(error.hasFieldErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(message,400));
        }
        Boolean isUpdateMerchant = merchantStockService.updateMerchantStocks(merchant,merchantId);
        if(!isUpdateMerchant){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("No updated",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("MerchantStock updated",200));

    }

    @DeleteMapping("/delete/{merchantId}")
    public ResponseEntity deleteMerchantStock(@PathVariable String merchantId){
        Boolean isDeleteMerchant = merchantStockService.deleteMerchantStocks(merchantId);
        if(!isDeleteMerchant){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("NO deleted",400));

        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("MerchantStock deleted",200));

    }
}
