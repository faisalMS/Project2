package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.Comment;
import com.example.ecommercewebsite.Model.Product;
import com.example.ecommercewebsite.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CommentService {


    private ArrayList<Comment> commentArrayList=new ArrayList<>();
    private final PurchaseHistoryService purchaseHistoryService;
    private final ProductService productService;
    private final UserService userService;
    public ArrayList<Comment> getComments(){
        return commentArrayList;
    }

    public Integer postComment(String userId,String productId,Comment comment){
        User taUser = userService.getUsers(userId);
        if(taUser == null){
            return -1;
        }
        boolean histories = purchaseHistoryService.histories(userId,productId);
        if(!histories)
        {
            return 0;
        }
        commentArrayList.add(comment);
        return 1;
    }
    public ArrayList<Comment> getProductComments(String productId){
        Product tarProduct=productService.getProducts(productId);
        if(tarProduct == null){
            return null;
        }
        return tarProduct.getComments();

    }
    public ArrayList<Comment>getFiveSComments(){
        ArrayList<Comment> comments1 = new ArrayList<>();
        for (Comment comment : commentArrayList) {
            if(comment.getRate() == 5){
                comments1.add(comment);
            }

        }
        return comments1;
    }
}
