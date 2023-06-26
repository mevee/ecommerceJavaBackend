package com.ecommerce.service.interfaces;

import com.ecommerce.model.Coupon;
import com.ecommerce.model.cart.UpdateCartModel;
import com.ecommerce.model.response.product.Product;

import java.util.List;

public interface CartService {
    UpdateCartModel updateCartQty(String action, String userId, String productId, String cartId, int quantity) throws Exception;
    UpdateCartModel removeFromCart(String productId, String cartId,int quantity) throws Exception;
    List<Product> getProducts()throws Exception;
    boolean applyCoupon(String couponId,String cartId) throws Exception;
    List<Coupon> getCoupons(String userId, String cartId) throws Exception;

}
