package com.example.ecommercewebsite.service;

import com.example.ecommercewebsite.modle.Cart;
import com.example.ecommercewebsite.modle.MerchantStock;
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
    private final PurchaseHistoryService purchaseHistoryService;
    private final MerchantStockService merchantStockService;

    public ArrayList<Cart> getCarts() {
        return cartList;
    }

    public Integer buyWithCart(Cart cart){
        User user = userService.getUser().get(hashCode());
        Integer userBalance = user.getBalance();
        Integer total = 0;
        for (Product item : cart.getProductlist()
        ) {
            Integer merchantStock  = merchantStockService.getMerchantStocks().indexOf(item);
            if(merchantStock < 1){
                return -1;
            }
            total += item.getPrice();
        }
        if(userBalance < total){
            return 0;
        }

        for (Product item:cart.getProductlist()
        ) {
            for (MerchantStock merchantStock: merchantStockService.getMerchantStocks()
            ) {
                if(merchantStock.getProductid().equals(item.getId())){
                    merchantStock.setStock(merchantStock.getStock()-1);
                }
            }

            purchaseHistoryService.addPurchaseHistory(cart.getUserid(), item.getId(), item.getPrice());
        }

        user.setBalance(userBalance - total);
        return 1;
    }
}