package com.ecommerce.model.request.product;

import com.ecommerce.model.DataModel;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ProductDetailRequest extends DataModel {
    String productId;
    String userId;
    String cartId;
}
