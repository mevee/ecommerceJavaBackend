package com.ecommerce.service;

import com.ecommerce.model.Coupon;
import com.ecommerce.model.cart.UpdateCartModel;
import com.ecommerce.model.response.product.Product;
import com.ecommerce.repository.repo.CartRepository;
import com.ecommerce.service.interfaces.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository repository;

    @Override
    public UpdateCartModel updateCartQty(String action, String userId, String productId, String cartId, int quantity) throws Exception {
        return repository.updateCartQty(action,userId, productId, cartId, quantity);
    }

    @Override
    public UpdateCartModel removeFromCart(String productId, String cartId, int quantity) throws Exception {
        return null;
    }

    @Override
    public List<Product> getProducts() throws Exception {
        return null;
    }

    @Override
    public boolean applyCoupon(String couponId, String cartId) throws Exception {
        return false;
    }

    @Override
    public List<Coupon> getCoupons(String userId, String cartId) throws Exception {
        return null;
    }
}
