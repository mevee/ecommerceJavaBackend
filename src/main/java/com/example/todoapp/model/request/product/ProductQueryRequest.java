package com.example.todoapp.model.request.product;

import com.example.todoapp.model.DataModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ProductQueryRequest extends DataModel {
    String categoryId;
    String searchQuery;
    String productId;
    String userId;
    String cartId;
    int page;
}
