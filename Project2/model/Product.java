package com.example.ecommercewebsite.Model;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.ArrayList;

@Data
public class Product {

    @NotBlank(message = "id can't be empty")
    @Size(min = 3,message = "id has to be 3 char at least")
    private String ID;
    @NotBlank(message = "name can't be empty")
    @Size( min = 3,message = "name has to be 3 char at least")
    private String name;
    @NotNull(message = "price can't be empty")
    @Positive(message = "price has to be greater than 0")
    private Integer price;
    @NotBlank(message = "category id can't be empty")
    @Size( min = 3,message = "category id has to be 3 char at least")
    private String categoryID;
    private ArrayList<Comment> comments;

    public Product(String ID, String name, int price, String categoryID) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.categoryID = categoryID;
        this.comments = new ArrayList<>();
    }
}
