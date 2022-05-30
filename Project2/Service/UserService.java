package com.example.ecommercewebsite.Service;


import com.example.ecommercewebsite.Model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {


    private ArrayList<User> users = new ArrayList<>();
    
    private final MerchantStockService merchantStockService;
    private final ProductService productService;
    private final PurchaseHistoryService purchaseHistoryService;
    
    
    public User getUsers(String userId) {
        return users.get(getUserId(userId));
    }
    public ArrayList<User> getUsers(){
        return users;
    }
    public Boolean addUsers(User user){
        return users.add(user);
    }
    public Boolean updateUsers(User user,String id){
        Integer tarUserId = getUserId(id);
        if(tarUserId == null){
            return false;
        }
        users.set(tarUserId,user);
        return true;
    }
    public Boolean deleteUsers(String id){
        Integer tarUserId = getUserId(id);
        if(tarUserId == null){
            return false;
        }
        users.remove(tarUserId);
        return true;
    }

    public Integer getUserId(String id){
        for (int i = 0; i <users.size() ; i++) {
            if(users.get(i).getId().equals(id)){
                return i;
            }
        }
        return null;
    }
    
    public Integer withOutCartCart(String userId,String productId,String merchantId){
        MerchantStock tarMerchant = merchantStockService.getMerchantStocks(merchantId);
        if(tarMerchant.getStock()<1){
            return -1;
        }
        User tarUser = getUsers(userId);
        Product tarProduct = productService.getProducts(productId);
        if(tarUser.getBalance() < tarProduct.getPrice()){
            return 0;
        }
        tarMerchant.setStock(tarMerchant.getStock() -1);

        tarUser.setBalance(tarUser.getBalance() - tarProduct.getPrice());
        purchaseHistoryService.histories(userId,productId);
        return 1;
    }


    public ArrayList<PurchaseHistory> getHistories(String userId){
        User tarUser = getUsers(userId);
        if(tarUser == null){
            return null;
        }
        ArrayList<PurchaseHistory> userHistory = new ArrayList<>();
        for (PurchaseHistory item : purchaseHistoryService.getPurchaseHistories()) {
            if(item.getUserId().equals(userId)){
                userHistory.add(item);
            }
        }
        return userHistory;
    }


    public Integer WithCarts(Cart cart){
        User user= users.get(getUserId(cart.getUserId()));
        Integer userBalance = user.getBalance();
        int total = 0;
        for (Product item : cart.getProducts()) {
            Integer merchantStock = merchantStockService.getStocks(item.getID());
            if(merchantStock < 1){
                return -1;
            }
            total += item.getPrice();
        }
        if(userBalance < total){
            return 0;
        }
        for (Product item : cart.getProducts() {
            for (MerchantStock merchantStock: merchantStockService.getMerchantStocks()){
                if(merchantStock.getProductId().equals(item.getID())){
                    merchantStock.setStock(merchantStock.getStock() -1);
                }
            }
            purchaseHistoryService.histories(cart.getUserId(), item.getID());
        }
        user.setBalance(userBalance - total);
        return 1;
    }
}
