package com.ecommerce.service;

import com.ecommerce.model.Coupon;
import com.ecommerce.model.cart.Cart;
import com.ecommerce.model.cart.Boolean;
import com.ecommerce.model.request.cart.GetCartDetailRequest;
import com.ecommerce.model.response.product.InventoryProduct;
import com.ecommerce.model.response.product.Product;
import com.ecommerce.repository.repo.CartRepository;
import com.ecommerce.repository.repo.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    @Override
    public Boolean updateCartQty(String action, String userId, String productId, String cartId, int quantity) throws Exception {
        return repository.updateCartQty(action, userId, productId, cartId, quantity);
    }

    @Override
    public boolean removeFromCart(String productId, String cartId, int quantity) throws Exception {
        return repository.deleteFromCart(productId, cartId, quantity);
    }

    @Override
    public List<Product> getProducts() throws Exception {
//        return repository.getProducts();
        return null;

    }

    @Override
    public Cart getCart(GetCartDetailRequest request) throws Exception {
        if (!userRepository.isUserExist(""+request.getUserId())){
            throw new Exception("Invalid user");
        }
        Cart cart = new Cart();
        InventoryProduct products = repository.getProducts("" + request.getCartId());
        cart.setId("" + request.getCartId());
        if (products != null) {
            cart.setCartProducts(products.getProducts());
            cart.setTotalQuantity(products.getProducts().size());
        }
        cart.setDeliveryCharges("0");
        cart.setTotalPayable(repository.getTotalCart("" + request.getCartId()));

        return cart;
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
