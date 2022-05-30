package com.example.ecommercewebsite.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data @AllArgsConstructor
public class Comment {

    @NotBlank(message = "id can't be empty")
    @Size(min = 3,message = "id has to be at least 3 char")
    private String id;
    @NotBlank(message = "user id can't be empty")
    @Size(min = 3,message = "user id has to be at least 3 char")
    private String userId;
    @NotBlank(message = "message can't be empty")
    @Size(min = 6,message = "message has to be at least 6 char")
    private String message;
    @NotBlank(message = "rate can't be empty")
    @Size(min = 1, max = 5,message = "rating has to be between 1 and 5")
    private Integer rate;
}
