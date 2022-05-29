package com.example.ecommercewebsite.controller;

import com.example.ecommercewebsite.modle.Api;
import com.example.ecommercewebsite.modle.Cart;
import com.example.ecommercewebsite.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<ArrayList<Cart>> getCarts() {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getCarts());
    }


    @GetMapping("/{carId}")
    public ResponseEntity getCart(@PathVariable String carId){
        Cart tcar = cartService.findCart(carId);
        if(car == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Invalid id",500));
        }
        return ResponseEntity.status(HttpStatus.OK).body(target_cart);
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestParam String cartID,@RequestParam String userID,@RequestParam String productID){
        Integer productAdditionCase=cartService.addProduct(cartID,userID,productID);
        switch (productAdditionCase){
            case -1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Invalid cart id",400));
            case 0:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Invalid user id",400));
            case 1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Invalid product id",400));
            case 2:
                return ResponseEntity.status(HttpStatus.OK).body(new Api("Product added",201));
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Server Error",500));

        }
    }

    @PutMapping("/remove")
    public ResponseEntity removeProduct(@RequestParam String cartId,@RequestParam String userId,@RequestParam String productId){
        Integer productAdditionCase =c artService.removeProduct(cartId,userId,productId);
        switch (productAdditionCase){
            case -1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Invalid cart id",400));
            case 0:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Invalid user id",400));
            case 1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Invalid user id",400));
            case 2:
                return ResponseEntity.status(HttpStatus.OK).body(new Api("Product removed",201));
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Server Error",500));

        }
    }

}
