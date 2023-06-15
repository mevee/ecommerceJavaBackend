package com.example.todoapp.model.cart;

import com.example.todoapp.model.DataModel;
import com.example.todoapp.model.response.product.Product;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class Cart extends DataModel {
    String id;
    int totalQuantity;
    String description;
    String offer;
    String deliveryCharges;
    List<Product> cartProducts;

}
