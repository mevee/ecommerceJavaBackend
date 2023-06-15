package com.example.todoapp.model.response.product;

import com.example.todoapp.model.ApiResponse;
import com.example.todoapp.model.DataModel;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class Product extends DataModel {
    int id;
    int price;
    int inventoryQty;
    int unit;
    int weight;
    String name;
    String description;
    String fullDesc;
    List<String> images;
    String highValue;
    String inCart;
    List<ProductVariants> variants;

}
