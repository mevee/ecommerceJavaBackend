package com.ecommerce.repository.repo;

import com.ecommerce.model.Coupon;
import com.ecommerce.model.cart.Boolean;
import com.ecommerce.model.response.product.InventoryProduct;

import java.util.List;

public interface CartRepository {
    /*Cart Story
     * create a cart
     * delete a cart
     * contains all the product items
     * cart total value sum of all product selling at
     * cart discounted value overall discount
     * offer applied or apply offer (Not sure)
     * totalSavings
     * userAddress shipping and billing address
     * add to cart
     * remove from cart
     * update a cart quantity
     *
     * */

    int getTotalCart(String cartId);

    int getLastProductQty(String id);
    int getIdOfProduct(String cartId,String productId);
    int getCartId(String userId);
    boolean isProductExistInCart(String id, String productId) throws Exception;
    boolean isValidCart(String cartId) throws Exception;
    Boolean updateCartQty(String action, String userId, String productId, String cartId, int quantity) throws Exception;

    int createCart() throws Exception;
    int addToCart() throws Exception;
    boolean deleteFromCart(String productId, String cartId, int quantity) throws Exception;
    InventoryProduct getProducts(String cartId)throws Exception;
    boolean applyCoupon(String couponId,String cartId) throws Exception;
    List<Coupon> getCoupons(String userId, String cartId) throws Exception;
    boolean deleteCart(String cartId) throws Exception;

}
