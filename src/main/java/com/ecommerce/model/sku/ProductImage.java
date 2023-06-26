package com.ecommerce.model.sku;

import com.ecommerce.model.DataModel;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ProductImage extends DataModel {
    String id;
    String pId;
    String image;
}
