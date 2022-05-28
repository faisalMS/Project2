package com.example.ecommercewebsite.service;

import com.example.ecommercewebsite.modle.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CommentService {

    private ArrayList<Comment> commentArrayList = new ArrayList<>();
    private final ProductService productService;

    private final PurchaseHistoryService purchaseHistoryService;

    private final UserService userService;

    public ArrayList<Comment> getComments() {
        return commentArrayList;
    }

    public Integer postCommOnProduct(String userId, String productId, Comment comment) {
        User target_user = userService.UsersID(userId);
        if (target_user == null) {
            return -1;
        }
        boolean histories = purchaseHistoryService.addPurchaseHistory(userId, productId, comment);
        if (!histories) {
            return 0;
        }
        commentArrayList.add(comment);
        return 1;
    }
}