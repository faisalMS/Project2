package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite2.Model.Api;
import com.example.ecommercewebsite2.Model.Product;
import com.example.ecommercewebsite2.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {



    private final ProductService productService;

    @GetMapping
    public ResponseEntity getProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }

    @PostMapping
    public ResponseEntity addProducts(@RequestBody @Valid Product product, Errors error){
        if(error.hasFieldErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(message,400));
        }
        Boolean isAddProduct = productService.addProducts(product);
        if(!isAddProduct){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Product not added",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Product added",200));
    }
    @PutMapping("update/{productId}")
    public ResponseEntity updateProducts(@RequestBody @Valid Product product , @PathVariable String productId, Errors error){
        if(error.hasFieldErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(message,400));
        }
        Boolean isUpdateProduct = productService.updateProducts(product,productId);
        if(!isUpdateProduct){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("No updated",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Product updated",200));

    }
    @DeleteMapping("delete/{productId}")
    public ResponseEntity deleteProducts(@PathVariable String productId){
        Boolean isDeleteProduct = productService.deleteProducts(productId);
        if(!isDeleteProduct){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Product not deleted",400));

        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Product deleted",200));

    }
}
