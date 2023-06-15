package com.example.todoapp.model.request.product;

import com.example.todoapp.model.DataModel;
import com.example.todoapp.model.response.product.ProductVariants;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class AddProductRequest extends DataModel {
    int id;
    int sku;
    int price;
    int inventoryQty;
    String tags;
}
