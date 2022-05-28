package com.example.ecommercewebsite.modle;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.util.ArrayList;

@AllArgsConstructor
@Data
public class Comment {

    @NotBlank(message = "Id is required")
    @Size(min = 3, message = "Have to be 3 character long")
    private String id;
    @NotBlank(message = "UserId is required")
    @Size(min = 5, message = "Have to be 5 length long")
    private String userid;
    @NotBlank(message = "Message is required")
    @Size(min = 6, message = "Have to be 6 length long")
    private String message;
    @NotNull(message = "Rate is required")
    @Range(min = 1, max = 5, message = "must be a number between 1 - 5")
    private Integer rate;
}
