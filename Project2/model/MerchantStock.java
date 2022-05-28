package com.example.ecommercewebsite.modle;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class MerchantStock {

    @NotBlank(message = "Id is required")
    @Size(min = 3, message = "Have to be 3 character long")
    private String id;
    @NotBlank(message = "ProductId is required")
    @Size(min = 3, message = "Have to be 3 character long")
    private String productid;
    @NotBlank(message = "MerchantId is required")
    @Size(min = 3, message = "Have to be 3 length long")
    private String merchantid;
    @NotBlank(message = "Stock is required")
    @Min(value = 10, message = "have to be more than 10 at start")
    private Integer stock;

}
