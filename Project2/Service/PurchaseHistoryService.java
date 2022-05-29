package com.example.ecommercewebsite.service;

import com.example.ecommercewebsite.modle.PurchaseHistory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PurchaseHistoryService {


    private ArrayList<PurchaseHistory> purchaseHistoryList = new ArrayList<>();

    public ArrayList<PurchaseHistory> getPurchaseHistories() {
        return purchaseHistoryList;
    }


    public Boolean addPurchaseHistory2(String userId,String productId){
        boolean histories = false;
        for (PurchaseHistory item : purchaseHistoryList) {
            if(item.getUserid().equals(userId)&&item.getProductid().equals(productId)){
                histories = true;
            }
        }
        return histories;
    }

}