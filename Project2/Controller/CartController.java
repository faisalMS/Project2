package com.example.ecommercewebsite.controller;

import com.example.ecommercewebsite.modle.Api;
import com.example.ecommercewebsite.modle.Cart;
import com.example.ecommercewebsite.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping ("/buyProwithCart")
    public ResponseEntity<Api> buyProwithCart(@RequestBody @Valid  Cart cart) {
        Integer buyProwithCart = cartService.buyWithCart(cart);
        switch (buyProwithCart){
            case -1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(" not found",400));
            case 0:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("doesn't sell this product",400));
            case 1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Out of stock",400));
            case 2:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("doesn't have enough balance",400));
            case 3:
                return ResponseEntity.status(HttpStatus.OK).body(new Api(" completed",200));
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Server error",500));
        }
    }
}
