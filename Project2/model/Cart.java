package com.example.ecommercewebsite2\.Model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
public class Cart {

    @NotBlank(message = "id can't be empty")
    @Size(min = 3,message = "id has to be 3 char at least")
    private String id;
    @NotBlank(message = "user id can't be empty")
    @Size(min = 3,message = "user id has to be 3 char at least")
    private String userId;
    private ArrayList<Product> products;

    public Cart(String id, String userId) {
        this.id = id;
        this.userId = userId;
        this.products = new ArrayList<>();
    }
}
