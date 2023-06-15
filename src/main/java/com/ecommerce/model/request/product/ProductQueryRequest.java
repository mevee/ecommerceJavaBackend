package com.ecommerce.model.request.product;

import com.ecommerce.model.DataModel;
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
