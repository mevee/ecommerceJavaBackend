package com.ecommerce.model.request.cart;

import com.ecommerce.model.DataModel;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class GetCartDetailRequest extends DataModel {
    int userId;
    int cartId;
}
