package com.example.ecommercewebsite.modle;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.stream.events.Comment;
import java.util.ArrayList;

@AllArgsConstructor
@Data
public class Merchant {

    @NotBlank(message = "Id is required")
    @Size(min = 3, message = "Have to be 3 character long")
    private String id;
    @NotEmpty(message = "Name is required")
    @Size(min = 3, message = "Have to be 3 length long")
    private String name;
}
