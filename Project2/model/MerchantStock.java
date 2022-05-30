package com.example.ecommercewebsite.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class MerchantStock {

    @NotBlank(message = "id can't be empty")
    @Size(min = 3,message = "id has to be 3 char at least")
    private String ID;
    @NotBlank(message = "product id can't be empty")
    @Size( min = 3,message = "product id has to be 3 char at least")
    private String productId;
    @NotEmpty(message = "merchant id can't be empty")
    @Size( min = 3,message = "merchant id has to be 3 char at least")
    private String merchantId;
    @NotNull(message = "stock can't be empty")
    @Min( value = 11,message = "stock has to be more than 10")
    private Integer stock;
}
