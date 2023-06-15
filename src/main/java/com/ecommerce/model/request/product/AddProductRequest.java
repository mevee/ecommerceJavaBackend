package com.ecommerce.model.request.product;

import com.ecommerce.model.DataModel;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class AddProductRequest extends DataModel {
    int id;
    int sku;
    int price;
    int inventoryQty;
    String tags;
}
