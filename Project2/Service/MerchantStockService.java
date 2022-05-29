package com.example.ecommercewebsite.service;

import com.example.ecommercewebsite.modle.MerchantStock;
import com.example.ecommercewebsite.modle.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

    private ArrayList<MerchantStock> merchantStockList = new ArrayList<>();
    private final UserService userService;
    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStockList;
    }

    public boolean addMerchantStocks(MerchantStock merchantStock){
        return merchantStockList.add(merchantStock);
    }

    public Boolean deleteMerchantStocks(String merchantStock) {
        for (int i = 0; i < merchantStockList.size(); i++) {
            if (merchantStockList.get(i).getId().equals(merchantStock)) {
                merchantStockList.remove(i);
                return true;
            }
        }
        return false;
    }

    public Boolean updateMerchantStocks(MerchantStock merchantStock) {
        for (int i = 0; i < merchantStockList.size(); i++) {
            if (merchantStockList.get(i).getId().equals(merchantStock.getId())) {
                merchantStockList.set(i,merchantStock);
                return true;
            }
        }
        return false;
    }

    public MerchantStock merchantStockProductsID(String merchantStockProductIDid){
        for (MerchantStock merchantStock:merchantStockList) {
            if(merchantStockProductIDid.equals(merchantStock.getId())){
                return merchantStock;
            }
        }
        return null;
    }

    public Integer addPToMS(String userId, String merchantId, Integer additional_stock){

        User user = userService.getUsersID(userId);
        if(user == null){
            return -1;
        }
        MerchantStock merchantStock = merchantStockProductsID(merchantId);
        if(merchantStock == null){
            return 0;
        }
        merchantStock.setStock(merchantStock.getStock()+additional_stock);
        return 1;
    }


}
