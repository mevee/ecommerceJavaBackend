package com.ecommerce.model.cart;

import com.ecommerce.model.DataModel;
import com.ecommerce.model.response.product.Product;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class Cart extends DataModel {
    String id;
    int totalQuantity;
    String description;
    String coupon;
    String deliveryCharges;
    List<Product> cartProducts;

}
