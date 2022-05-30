package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.PurchaseHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PurchaseHistoryService {

    private ArrayList<PurchaseHistory> purchaseHistorylist = new ArrayList<>();

    public ArrayList<PurchaseHistory> getPurchaseHistories(){
        return purchaseHistorylist;
    }

    public Boolean histories(String userId, String productId){
        boolean histories = false;
        for (PurchaseHistory item : purchaseHistorylist) {
            if(item.getUserId().equals(userId)&&item.getProductId().equals(productId)){
                histories = true;
            }
        }
        return histories;
    }
}
