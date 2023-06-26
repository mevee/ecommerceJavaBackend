package com.ecommerce.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Coupon extends DataModel {
    String id;
    String title;
    int discountValue;
}
