package com.example.ecommercewebsite.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor @Data
public class Category {

    @NotBlank(message = "id can't be empty")
    @Size(min = 3,message = "id has to be 3 char at least")
    private String ID;
    @NotBlank(message = "name can't be empty")
    @Size( min = 3,message = "name has to be 3 char at least")
    private String name;
}
