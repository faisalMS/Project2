package com.example.ecommercewebsite.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class User {

    @NotBlank(message = "id can't be empty")
    @Size(min = 3,message = "id has to be 3 char at least")
    private String id;
    @NotBlank(message = "username can't be empty")
    @Size(min = 5,message = "username has to be 5 char at least")
    private String username;
    @NotBlank(message = "password can't be empty")
    @Size(min = 6,message = "username has to be 6 char at least")
    @Pattern(regexp =  "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[!@#&()–[{}]:;',?/*~$^+=<>]).{6,20}$",message = "password invalid - password has to be at least 6 char and must have characters and digits")
    private String password;
    @NotBlank(message = "email can't be empty")
    @Email(message = "email invalid")
    private String email;
    @NotBlank(message = "role can't be empty")
    @Pattern(regexp = "(rollercoaster|thriller|water)",message = "invalid role")
    private String role;
    @NotBlank(message = "balance can't be empty")
    @Positive(message = "balance has to be more than 0")
    private Integer balance;
}
