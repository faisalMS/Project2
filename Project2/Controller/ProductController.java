package com.example.ecommercewebsite.controller;

import com.example.ecommercewebsite.modle.Api;
import com.example.ecommercewebsite.modle.Product;
import com.example.ecommercewebsite.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ArrayList<Product>> getProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }

    @PostMapping
    public ResponseEntity<Api> addProducts(@RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isAddProducts = productService.addProducts(product);
        if (!isAddProducts) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Error adding a product", 500));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("New product added", 200));
    }

    @DeleteMapping("/{productID}")
    public ResponseEntity<Api> deleteProducts(@PathVariable String productID) {
        Boolean deleteProducts = productService.deleteProducts(productID);
        if (!deleteProducts) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("productID doesn't exists!",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("productID deleted!",200));
    }


    @PutMapping
    public ResponseEntity<Api> updateProducts(@RequestBody @Valid Product product, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        Boolean updateProducts = productService.updateProducts(product);
        if (!updateProducts) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Advisor edited!",500));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("advisorID doesn't exists!",200));
    }
}
