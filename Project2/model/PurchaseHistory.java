package com.example.ecommercewebsite2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@AllArgsConstructor @Data
public class PurchaseHistory {
    @NotBlank(message = "id can't be empty")
    @Size(min = 3,message = "id has to be at least 3 char")
    private String id;
    @NotBlank(message = "user id can't be empty")
    @Size(min = 3,message = "user id has to be at least 3 char")
    private String userId;
    @NotBlank(message = "product id can't be empty")
    @Size(min = 3,message = "product id has to be at least 3 char")
    private String productId;
    @NotBlank(message = "balance can't be empty")
    @Positive(message = "balance has to be more than 0")
    private Integer price;
}
