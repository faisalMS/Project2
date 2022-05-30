package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {

    private ArrayList<Merchant> merchantlist = new ArrayList<>();

    public ArrayList<Merchant> getMerchants(){
        return merchantlist;
    }

    public Integer MerchantId(String id){
        for (int i = 0; i <merchantlist.size() ; i++) {
            if(merchantlist.get(i).getID().equals(id)){
                return i;
            }
        }
        return null;
    }

    public Boolean addMerchants(Merchant merchant){
        return merchantlist.add(merchant);
    }

    public Boolean updateMerchants(Merchant merchant,String id){
        Integer tarMerchantId = MerchantId(id);
        if(tarMerchantId == null){
            return false;
        }
        merchantlist.set(tarMerchantId,merchant);
        return true;
    }

    public Boolean deleteMerchants(String id){
        Integer tarMerchantId = MerchantId(id);
        if(tarMerchantId == null){
            return false;
        }
        merchantlist.remove(tarMerchantId);
        return true;
    }

}
