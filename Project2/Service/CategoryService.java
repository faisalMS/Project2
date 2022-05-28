package com.example.ecommercewebsite.service;

import com.example.ecommercewebsite.modle.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class CategoryService {


    private ArrayList<Category> categoryArrayList = new ArrayList<>();

    public ArrayList<Category> getCategories() {
        return categoryArrayList;
    }

    public boolean addCategories(Category category){
        return categoryArrayList.add(category);
    }

    public Boolean deleteCategories(String category) {
        for (int i = 0; i < categoryArrayList.size(); i++) {
            if (categoryArrayList.get(i).getId().equals(category)) {
                categoryArrayList.remove(i);
                return true;
            }
        }
        return false;
    }

    public Boolean updateCategories(Category category) {
        for (int i = 0; i < categoryArrayList.size(); i++) {
            if (categoryArrayList.get(i).getId().equals(category.getId())) {
                categoryArrayList.set(i,category);
                return true;
            }
        }
        return false;
    }
}
