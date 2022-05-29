package com.example.ecommercewebsite.controller;

import com.example.ecommercewebsite.modle.Api;
import com.example.ecommercewebsite.modle.Comment;
import com.example.ecommercewebsite.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<ArrayList<Comment>> getComments() {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getComments());
    }

    @PostMapping("/postCommOnProduct/{userId}/{productId}")
    public ResponseEntity<Api> postCommOnProduct(@PathVariable String userId, @PathVariable String productId, Comment comment) {
        Integer postCommOnProduct = commentService.postCommOnProduct(userId, productId, comment);
        switch (postCommOnProduct) {
            case -1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Merchant not found", 400));
            case 0:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Merchant doesn't sell this product", 400));
            case 1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Out of stock", 400));
            case 2:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("User doesn't have enough balance", 400));
            case 3:
                return ResponseEntity.status(HttpStatus.OK).body(new Api("Purchase completed", 200));
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Server error", 500));

        }

    }
    @GetMapping("/rating_five")
    public ResponseEntity getFiveComments(){
        ArrayList<Comment> FiveComments = commentService.getComments();
        if(FiveComments == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Product comments not found",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(FiveComments);
    }
}
