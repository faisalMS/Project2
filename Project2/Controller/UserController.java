package com.example.ecommercewebsite.controller;

import com.example.ecommercewebsite.modle.Api;
import com.example.ecommercewebsite.modle.Comment;
import com.example.ecommercewebsite.modle.PurchaseHistory;
import com.example.ecommercewebsite.modle.User;
import com.example.ecommercewebsite.service.UserService;
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
    public ResponseEntity<ArrayList<User>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser());
    }

    @PostMapping
    public ResponseEntity<Api> addUsers(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isAddUsers = userService.addUsers(user);
        if (!isAddUsers) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Error adding a user", 200));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("New user added", 200));
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<Api> deleteUsers(@PathVariable String userID) {
        Boolean deleteUsers = userService.deleteUsers(userID);
        if (!deleteUsers) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("userID doesn't exists!",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("userID deleted!",200));
    }


    @PutMapping
    public ResponseEntity<Api> updateUsers(@RequestBody @Valid User user, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        Boolean updateUsers = userService.updateUsers(user);
        if (!updateUsers) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Advisor edited!",500));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("advisorID doesn't exists!",200));
    }

    @PostMapping("/buyProDirWithOCart/{userId}/{productId}/{merchantId}")
    public ResponseEntity<Api> buyProDirWithOCart(@PathVariable String userId,@PathVariable String productId,@PathVariable String merchantId){
        Integer buyProDirWithOCart = userService.buyProDirWithOCart(userId, productId, merchantId);
        switch (buyProDirWithOCart){
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

    @GetMapping("/AllPurchaseHistory/{userID}")
    public ResponseEntity<ArrayList<PurchaseHistory>> AllPurchaseHistory(@PathVariable String userID){
        return ResponseEntity.status(HttpStatus.OK).body(userService.AllPurchaseHistory(userID));
    }
}
