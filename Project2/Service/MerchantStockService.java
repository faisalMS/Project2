package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.Merchant;
import com.example.ecommercewebsite.Model.MerchantStock;
import com.example.ecommercewebsite.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

    private ArrayList<MerchantStock> merchantstocklist = new ArrayList<>();
    private final ProductService productService;

    public Integer getStocks(String productId){
        for (MerchantStock merchantStock : merchantstocklist){
            if (merchantStock.getProductId().equals(productId)){
                return merchantStock.getStock();
            }
        }

        return null;
    }

    public ArrayList<MerchantStock> getMerchantStocks(){
        return merchantstocklist;
    }

    public Integer getMerchantStockId(String id){
        for (int i = 0; i <merchantstocklist.size() ; i++) {
            if(merchantstocklist.get(i).getID().equals(id)){
                return i;
            }
        }
        return null;
    }

    public MerchantStock getMerchantStocks(String id){
        for (int i = 0; i <merchantstocklist.size() ; i++) {
            if(merchantstocklist.get(i).getID().equals(id)){
                return merchantstocklist.get(i);
            }
        }
        return null;
    }

    public Boolean addMerchantStocks(MerchantStock merchantStock){
        return merchantstocklist.add(merchantStock);
    }
    public Boolean updateMerchantStocks(MerchantStock merchantStock,String id){
        Integer tarMerchantStockId = getMerchantStockId(id);
        if(tarMerchantStockId == null){
            return false;
        }
        merchantstocklist.set(tarMerchantStockId,merchantStock);
        return true;
    }
    public Boolean deleteMerchantStocks(String id){
        Integer tarMerchantStockId = getMerchantStockId(id);
        if(tarMerchantStockId == null){return false;}
        merchantstocklist.remove(tarMerchantStockId);
        return true;
    }
    public Integer addProductStocks(String merchantId,String productId,Integer merchantStock){
        MerchantStock tarMerchant = getMerchantStocks(merchantId);
        if(tarMerchant == null){
            return -1;
        }
        Product tarProduct = productService.getProducts(productId);
        if(tarProduct == null){
            return 0;
        }
        tarMerchant.setStock(tarMerchant.getStock() + merchantStock);
        return 1;
    }
}
