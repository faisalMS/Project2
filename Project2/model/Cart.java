package com.example.ecommercewebsite.modle;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
public class Cart {

    @NotBlank(message = "Id is required")
    @Size(min = 3, message = "Have to be 3 character long")
    private String id;
    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Have to be 3 character long")
    private String userid;
    private ArrayList<Product> productlist;

    public Cart(String id, String userid, ArrayList<Product> productlist) {
        this.id = id;
        this.userid = userid;
        this.productlist = new ArrayList<>();
    }
}
