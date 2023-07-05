package com.ecommerce.model.cart;

import com.ecommerce.model.DataModel;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Boolean extends DataModel {
    int productId;
    int userId;
    int cartId;
    int qty;
}
