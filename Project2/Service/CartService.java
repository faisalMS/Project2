package com.example.ecommercewebsite.service;

import com.example.ecommercewebsite.modle.Cart;
import com.example.ecommercewebsite.modle.Product;
import com.example.ecommercewebsite.modle.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {


    private ArrayList<Cart> cartList = new ArrayList<>();

    private final UserService userService;


    private final ProductService productService;

    public ArrayList<Cart> getCarts() {
        return cartList;
    }



    public Integer addProduct(String cartId, String userId, String productId){
        Cart cart = findCart(cartId);
        if(cart == null){
            return -1;
        }
        User user = userService.getUsersID(userId);
        if(user == null){
            return 0;
        }
        Product product = productService.getProduct(productId);
        if(product == null){
            return 1;
        }
        cart.getProducts().add(product);
        return 2;

    }

    public Integer removeProduct(String cartId, String userID, String productID){
        Cart cart = findCart(cartId);
        if(cart == null){
            return -1;
        }
        User user = userService.getUsersID(userID);
        if(user == null){
            return 0;
        }
        Product product = productService.getProduct(productID);
        if(product == null){
            return 1;
        }
        cart.getProducts().remove(product);
        return 2;

    }

    public Cart findCart(String cartID){
        for (Cart cart : cartList
        ) {
            if(cart.getId().equals(cartID)){
                return cart;
            }
        }
        return null;
    }


}