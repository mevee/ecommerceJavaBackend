package com.ecommerce.model.response.product;

import com.ecommerce.model.DataModel;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class InventoryProduct extends DataModel {
    boolean hasNext = false;
    int nextIndex = 0;
    List<Product> products;
}
