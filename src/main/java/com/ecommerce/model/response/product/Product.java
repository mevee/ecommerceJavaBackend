package com.ecommerce.model.response.product;

import com.ecommerce.model.DataModel;
import com.ecommerce.model.sku.ProductImage;
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
    List<ProductImage> images;
    String highValue;
    String inCart;
    List<ProductVariants> variants;

}
