package com.example.ecommercewebsite.modle;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import javax.xml.stream.events.Comment;
import java.util.ArrayList;

 @Data
public class Product {

    @NotBlank(message = "Id is required")
    @Size(min = 3, message = "Have to be 3 character long")
    private String id;
    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "have to be 3 length long")
    private String name;
    @NotNull(message = "Price is required")
    @Positive(message = "Must be positive number")
    private Integer price;
    @NotBlank(message = "CategoryId is required")
    @Size(min = 3, message = "Have to be 3 character long")
    private String categoryid;
    private ArrayList<Comment> commentlist;

     public Product(String id, String name, Integer price, String categoryid, ArrayList<Comment> commentlist) {
         this.id = id;
         this.name = name;
         this.price = price;
         this.categoryid = categoryid;
         this.commentlist =new ArrayList<>();
     }
 }
