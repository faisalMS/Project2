package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.Model.Api;
import com.example.ecommercewebsite.Model.Comment;
import com.example.ecommercewebsite.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;


@RestController
@RequestMapping("api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    @GetMapping
    public ResponseEntity getComments(){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getComments());
    }

    @GetMapping("/{productId}")
    public ResponseEntity getProductComments(@PathVariable String productId){
        ArrayList<Comment> productComments = commentService.getProductComments(productId);
        if(productComments == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("No found",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(productComments);
    }
    
    @GetMapping("/ratingFive")
    public ResponseEntity getFiveComments(){
        ArrayList<Comment> fiveComments = commentService.getFiveSComments();
        if(fiveComments == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("No found",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(fiveComments);
    }

    @PostMapping("post/{userId}/{productId}")
    public ResponseEntity postComment(@RequestBody @Valid Comment comment, @PathVariable String userId, @PathVariable String productId, Errors error){
        if(error.hasFieldErrors()){
            String massge =e rror.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(massge,400));
        }
        Integer commentStatus=commentService.postComment(userId,productId,comment);
        switch (commentStatus){
            case -1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("User no found",400));
            case 0:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("No found",400));
            case 1:
                return ResponseEntity.status(HttpStatus.OK).body(new Api("Comment successfully",201));
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Server Error",500));

        }

    }
}

