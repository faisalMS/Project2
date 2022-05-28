package com.example.ecommercewebsite.service;

import com.example.ecommercewebsite.modle.Merchant;
import com.example.ecommercewebsite.modle.MerchantStock;
import com.example.ecommercewebsite.modle.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {

    private ArrayList<Merchant> merchantArrayList = new ArrayList<>();


    public ArrayList<Merchant> getMerchants() {
        return merchantArrayList;
    }

    public boolean addMerchants(Merchant merchant) {
        return merchantArrayList.add(merchant);
    }

    public Boolean deleteMerchants(String merchant) {
        for (int i = 0; i < merchantArrayList.size(); i++) {
            if (merchantArrayList.get(i).getId().equals(merchant)) {
                merchantArrayList.remove(i);
                return true;
            }
        }
        return false;
    }

    public Boolean updateMerchants(Merchant merchant) {
        for (int i = 0; i < merchantArrayList.size(); i++) {
            if (merchantArrayList.get(i).getId().equals(merchant.getId())) {
                merchantArrayList.set(i,merchant);
                return true;
            }
        }
        return false;
    }

    public Merchant merchantsId(String merchantId){
        for( Merchant merchant : merchantArrayList){
            if(merchant.equals(merchantId)){
                return merchant;
            }
        }
        return null;
    }
}
