package com.example.ecommercewebsite.service;

import com.example.ecommercewebsite.modle.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CommentService {

    private ArrayList<Comment> commentArrayList = new ArrayList<>();

    private final UserService userService;
    private final ProductService productService;
    private final PurchaseHistoryService purchaseHistoryService;

    public ArrayList<Comment> getComments() {
        return commentArrayList;
    }

    public Integer postCommOnProduct(String userId, String productId, Comment comment) {
        User user = userService.getUsersID(userId);
        if (user == null) {
            return -1;
        }
        boolean histories = purchaseHistoryService.addPurchaseHistory2(userId, productId);
        if (!histories) {
            return 0;
        }
        commentArrayList.add(comment);
        return 1;
    }

    public ArrayList<Comment> getFiveComments(){
        ArrayList<Comment> commentArrayList1 = new ArrayList<>();
        for (Comment comment : commentArrayList) {
            if(comment.getRate() == 5){
                commentArrayList1.add(comment);
            }

        }
        return commentArrayList1;
    }
}