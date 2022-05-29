package com.example.ecommercewebsite.service;

import com.example.ecommercewebsite.modle.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    private ArrayList<User> userList = new ArrayList<>();
    private final ProductService productService;


    private final MerchantStockService merchantStockService;
    private final PurchaseHistoryService purchaseHistoryService;


    public ArrayList<User> getUser(){
        return userList;
    }
    public boolean addUsers(User user) {
        return userList.add(user);
    }

    public Boolean deleteUsers(String user) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId().equals(user)) {
                userList.remove(i);
                return true;
            }
        }
        return false;
    }

    public Boolean updateUsers(User user) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId().equals(user.getId())) {
                userList.set(i, user);
                return true;
            }
        }
        return false;
    }

    public User getUsersID(String userid) {
        for (User user : userList) {
            if (user.getId().equals(userid)) {
                return user;
            }
        }
        return null;

    }

    public Integer buyProDirWithOCart(String userId,String productId,String merchantId){
        MerchantStock merchantStock = merchantStockService.merchantStockProductsID(merchantId);
        if(merchantStock.getStock() < 1){
            return -1;
        }
        User user = getUsersID(userId);
        Product product = productService.productsId(productId);
        if(user.getBalance()<product.getPrice()){
            return 0;
        }
        merchantStock.setStock(merchantStock.getStock() -1);

        user.setBalance(user.getBalance()-product.getPrice());
        purchaseHistoryService.addPurchaseHistory2(userId, productId);
        return 1;
    }

    public ArrayList<PurchaseHistory> AllPurchaseHistory(String userID){
        ArrayList<PurchaseHistory> purchaseHistories = new ArrayList<>();
        for(int i = 0; i < purchaseHistoryService.getPurchaseHistories().size();i++){
            PurchaseHistory purchaseHistory = purchaseHistoryService.getPurchaseHistories().get(i);
            if(purchaseHistory.getUserid().equals(userID)){
                purchaseHistories.add(purchaseHistory);
            }
        }
        return purchaseHistories;
    }
}