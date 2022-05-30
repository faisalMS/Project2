package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.Cart;
import com.example.ecommercewebsite.Model.MerchantStock;
import com.example.ecommercewebsite.Model.Product;
import com.example.ecommercewebsite.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {




        private ArrayList<Cart> cartlist = new ArrayList();
        private final ProductService productService;
        private final UserService userService;


        public ArrayList<Cart> getCarts(){
            return cartlist;
        }


        public Integer addProducts(String cartID, String userId, String productId){
            Cart tarCart = getcartsId (cartID);
            if(tarCart == null){
                return -1;
            }
            User tarUser = userService.getUsers(userId);
            if(tarUser == null){
                return 0;
            }
            Product tarProduct = productService.getProducts(productId);
            if(tarProduct == null){
                return 1;
            }
            tarCart.getProducts().add(tarProduct);
            return 2;

        }
        public Integer deleteProducts(String cartID, String userID, String productID){
            Cart tarCart = getcartsId(cartID);
            if(tarCart == null){
                return -1;
            }
            User tarUser = userService.getUsers(userID);
            if(tarUser == null){
                return 0;
            }
            Product tarProduct=productService.getProducts(productID);
            if(tarProduct==null){
                return 1;
            }
            tarCart.getProducts().remove(tarProduct);
            return 2;

        }
        public Cart getcartsId(String cartID){
            for (Cart cart : cartlist
            ) {
                if(cart.getId().equals(cartID)){
                    return cart;
                }
            }
            return null;
        }
    }
