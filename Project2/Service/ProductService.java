package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    private ArrayList<Product> productArrayList =new ArrayList<>();
    public Product getProducts(String productID) {
        return productArrayList.get(getProductId(productID));
    }

    public Integer getProductId(String id){
        for (int i = 0; i < productArrayList.size() ; i++) {
            if(productArrayList.get(i).getID().equals(id)){
                return i;
            }
        }
        return null;
    }
    public ArrayList<Product> getProducts(){
        return productArrayList;
    }
    public Boolean addProducts(Product product){
        return productArrayList.add(product);
    }
    public Boolean updateProducts(Product product,String id){
        Integer tarProductId = getProductId(id);
        if(tarProductId == null){
            return false;
        }
        productArrayList.set(tarProductId,product);
        return true;
    }
    public Boolean deleteProducts(String id){
        Integer tarProductId = getProductId(id);
        if(tarProductId == null){
            return false;
        }
        productArrayList.remove(tarProductId);
        return true;
    }
}
