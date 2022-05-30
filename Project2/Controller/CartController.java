package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.Model.Api;
import com.example.ecommercewebsite.Model.Cart;
import com.example.ecommercewebsite.Service.CartService;
import com.example.ecommercewebsite.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {




        private final CartService cartService;
        @GetMapping
        public ResponseEntity getCarts(){
            return ResponseEntity.status(HttpStatus.OK).body(cartService.getCarts());
        }

        @GetMapping("/{cartId}")
        public ResponseEntity getCarts(@PathVariable String cartId){
            Cart tarCart = cartService.getcartsId(cartId);
            if(tarCart == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Invalid id",500));
            }
            return ResponseEntity.status(HttpStatus.OK).body(tarCart);
        }
    
        @PostMapping("/add")
        public ResponseEntity addProducts(@RequestParam String cartId,@RequestParam String userId,@RequestParam String productId){
            Integer addProduct = cartService.addProducts(cartId,userId,productId);
            switch (addProduct){
                case -1:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Invalid cart id",400));
                case 0:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Invalid user id",400));
                case 1:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Invalid product id",400));
                case 2:
                    return ResponseEntity.status(HttpStatus.OK).body(new Api("Product added",200));
                default:
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Server Error",500));

            }
        }
        @PutMapping("/delete")
        public ResponseEntity deleteProducts(@RequestParam String cartId,@RequestParam String userId,@RequestParam String productId){
            Integer deleteProduct = cartService.deleteProducts(cartId,userId,productId);
            switch (deleteProduct){
                case -1:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Invalid cart id",400));
                case 0:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Invalid user id",400));
                case 1:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Invalid product id",400));
                case 2:
                    return ResponseEntity.status(HttpStatus.OK).body(new Api("Product removed",200));
                default:
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Server Error",500));
            }
        }
}
