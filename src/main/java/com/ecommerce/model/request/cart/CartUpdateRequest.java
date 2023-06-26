package com.ecommerce.model.request.cart;

import com.ecommerce.model.DataModel;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CartUpdateRequest extends DataModel {
    int prodId;
    int userId;
    int cartId;
    int qty;
    String action;
}
