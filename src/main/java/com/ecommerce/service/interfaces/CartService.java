package com.ecommerce.service.interfaces;

import com.ecommerce.model.Coupon;
import com.ecommerce.model.cart.Cart;
import com.ecommerce.model.cart.Boolean;
import com.ecommerce.model.request.cart.GetCartDetailRequest;
import com.ecommerce.model.response.product.Product;

import java.util.List;

public interface CartService {
    Boolean updateCartQty(String action, String userId, String productId, String cartId, int quantity) throws Exception;
    boolean removeFromCart(String productId, String cartId, int quantity) throws Exception;
    List<Product> getProducts()throws Exception;
    Cart getCart(GetCartDetailRequest request)throws Exception;
    boolean applyCoupon(String couponId,String cartId) throws Exception;
    List<Coupon> getCoupons(String userId, String cartId) throws Exception;

}
