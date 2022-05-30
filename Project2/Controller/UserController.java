package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.Model.Api;
import com.example.ecommercewebsite.Model.Cart;
import com.example.ecommercewebsite.Model.PurchaseHistory;
import com.example.ecommercewebsite.Model.User;
import com.example.ecommercewebsite.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity getUsers() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity addUsers(@RequestBody @Valid User user, Errors error) {
        if (error.hasFieldErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(message, 400));
        }
        Boolean isAddUser = userService.addUsers(user);
        if (!isAddUser) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("User no added", 400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("User added", 200));
    }

    @PutMapping("/{userId}")
    public ResponseEntity updateUsers(@RequestBody @Valid User user, @PathVariable String userId, Errors error) {
        if (error.hasFieldErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(message, 400));
        }
        Boolean isUpdateUser = userService.updateUsers(user, userId);
        if (!isUpdateUser) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("NO updated", 400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("User updated", 200));

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUsers(@PathVariable String userId) {
        Boolean isDeleteUser = userService.deleteUsers(userId);
        if (!isDeleteUser) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("NO deleted", 400));

        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("User deleted", 200));

    }

    @PostMapping("/withOutCart")
    public ResponseEntity withOutCartCarts(@RequestParam String userId, @RequestParam String productId, @RequestParam String merchantId) {
        Integer withOutCartCarts = userService.withOutCartCart(userId, productId, merchantId);
        switch (withOutCartCarts) {
            case -1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Product out of stock", 400));
            case 0:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("No balance", 400));
            case 1:
                return ResponseEntity.status(HttpStatus.OK).body(new Api("Product successfully", 20));
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Server Error", 500));

        }
    }


    @PostMapping("/withCart")
    public ResponseEntity withCarts(@RequestBody @Valid Cart cart, Errors error) {
        if (error.hasFieldErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(message, 400));
        }

        Integer withCart = userService.WithCarts(cart);
        switch (withCart) {
            case -1:
                return ResponseEntity.status(HttpStatus.OK).body(new Api("Product out of stock", 400));
            case 0:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("No balance", 400));
            case 1:
                return ResponseEntity.status(HttpStatus.OK).body(new Api("Product successfully", 200));
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Server Error", 500));

        }
    }

    @GetMapping("histories")
    public ResponseEntity getPurchaseHistories (@RequestParam String userId){
        ArrayList<PurchaseHistory> histories = userService.getHistories(userId);
        if (histories == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("User not found", 400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(histories);
    }
}
