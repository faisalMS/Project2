package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.Model.Api;
import com.example.ecommercewebsite.Model.Category;
import com.example.ecommercewebsite.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    
    @GetMapping
    public ResponseEntity getCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategories());
    }
    
    @PostMapping
    public ResponseEntity addCategories(@RequestBody @Valid Category category, Errors error){
        if(error.hasFieldErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(message,400));
        }
        Boolean isAddCategories = categoryService.addCategories(category);
        if(!isAddCategories){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Category no added",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Category added",200));
    }
    
    @PutMapping("/{categoryId}")
    public ResponseEntity updateCategory(@RequestBody @Valid Category category , @PathVariable String categoryId, Errors error){
        if(error.hasFieldErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(message,400));
        }
        Boolean isUpdateCategory = categoryService.updateCategories(category,categoryId);
        if(!isUpdateCategory){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Category no updated",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Category updated",200));

    }
    
    @DeleteMapping("/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable String categoryId){
        Boolean isDeletedCategory = categoryService.deleteCategories(categoryId);
        if(!isDeletedCategory){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Category no deleted",400));

        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Category deleted",200));

    }
}
