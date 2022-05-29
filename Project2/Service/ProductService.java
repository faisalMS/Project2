package com.example.ecommercewebsite.service;


import com.example.ecommercewebsite.modle.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class ProductService {

    private ArrayList<Product> productArrayList = new ArrayList<>();

    public ArrayList<Product> getProducts(){
        return productArrayList;
    }

    public boolean addProducts(Product product){
        return productArrayList.add(product);
    }

    public Boolean deleteProducts(String product) {
        for (int i = 0; i < productArrayList.size(); i++) {
            if (productArrayList.get(i).getId().equals(product)) {
                productArrayList.remove(i);
                return true;
            }
        }
        return false;
    }

    public Boolean updateProducts(Product product) {
        for (int i = 0; i < productArrayList.size(); i++) {
            if (productArrayList.get(i).getId().equals(product.getId())) {
                productArrayList.set(i,product);
                return true;
            }
        }
        return false;
    }

    public Product getProduct(String productID) {
        return productArrayList.get(findProductIdx(productID));
    }
    public Product productsId(String productid){
        for( Product product : productArrayList){
            if(product.equals(productid)){
                return product;
            }
        }
        return null;
    }



    public Integer findProductIdx(String id){
        for (int i = 0; i <productArrayList.size() ; i++) {
            if(productArrayList.get(i).getId().equals(id)){
                return i;
            }
        }
        return null;
    }

}
