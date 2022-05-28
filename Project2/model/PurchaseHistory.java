package com.example.ecommercewebsite.modle;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import javax.xml.stream.events.Comment;
import java.util.ArrayList;

@AllArgsConstructor
@Data
public class PurchaseHistory {

    @NotBlank(message = "Id is required")
    @Size(min = 3, message = "Have to be 3 character long")
    private String id;
    @NotBlank(message = "UserId is required")
    @Size(min = 3, message = "Have to be 3 length long")
    private String userid;
    @NotBlank(message = "ProductId is required")
    @Size(min = 3, message = "Have to be 3 character long")
    private String productid;
    @NotNull(message = "Price is required")
    @Positive(message = "Must be a positive number")
    private Integer price;


}
