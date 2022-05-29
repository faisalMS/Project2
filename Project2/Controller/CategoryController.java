package com.example.ecommercewebsite.controller;

import com.example.ecommercewebsite.modle.Api;
import com.example.ecommercewebsite.modle.Category;
import com.example.ecommercewebsite.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ArrayList<Category>> getCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategories());
    }

    @PostMapping
    public ResponseEntity<Api> addCategories(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        boolean isAddCategories = categoryService.addCategories(category);
        if (!isAddCategories) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Error adding a category", 500));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("New category added", 200));
    }

    @DeleteMapping("/{categoryID}")
    public ResponseEntity<Api> deleteCategories(@PathVariable String categoryID) {
        Boolean deleteCategories = categoryService.deleteCategories(categoryID);
        if (!deleteCategories) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("CategoriesID doesn't exists!",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("CategoriesID deleted!",200));
    }

    @PutMapping
    public ResponseEntity<Api> updateCategories(@RequestBody @Valid Category category, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        Boolean updateCategories = categoryService.updateCategories(category);
        if (!updateCategories) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Advisor edited!",500));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("advisorID doesn't exists!",200));
    }
}
