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

    public boolean addPurchaseHistory(PurchaseHistory purchaseHistory){
        purchaseHistoryList.add(purchaseHistory);
        return true;
    }
}