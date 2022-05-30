package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {

    private ArrayList<Category> categorylist=new ArrayList<>();
    public ArrayList<Category> getCategories(){
        return categorylist;
    }

    public Integer CategoriesId(String id){
        for (int i = 0; i <categorylist.size() ; i++) {
            if(categorylist.get(i).getID().equals(id)){
                return i;
            }
        }
        return null;
    }

    public Boolean addCategories(Category category){
        return categorylist.add(category);
    }

    public Boolean updateCategories(Category category,String id){
        Integer tarCategoryId = CategoriesId(id);
        if(tarCategoryId == null){
            return false;
        }
        categorylist.set(tarCategoryId, category);
        return true;
    }

    public Boolean deleteCategories(String id){
        Integer tarCategoryId = CategoriesId(id);
        if(tarCategoryId == null){
            return false;
        }
        categorylist.remove(tarCategoryId);
        return true;
    }
}
