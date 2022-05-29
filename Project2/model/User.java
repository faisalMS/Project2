package com.example.ecommercewebsite.modle;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class User {

    @NotBlank(message = "Id is required")
    @Size(min = 3 , message = "Have to be 3 character long")
    private String id;
    @NotBlank(message = "Username is required")
    @Size(min = 5, message = "Have to be 5 length long")
    private String username;
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Have to be 6 length long")
    @Pattern(regexp = ("^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"), message = "Must have characters and digits")
    private String password;
    @NotBlank(message = "Email is required")
    @Email(message = "Must be valid email")
    private String email;
    @NotBlank(message = "Role is required")
    @Pattern(regexp = ("Admin|Customer"))
    private String role;
    @NotNull(message = "Balance is required")
    @Positive(message = "Have to be positive")
    private Integer balance;
}
