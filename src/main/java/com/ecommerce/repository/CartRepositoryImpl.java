package com.ecommerce.repository;

import com.ecommerce.model.Coupon;
import com.ecommerce.model.cart.Boolean;
import com.ecommerce.model.response.product.InventoryProduct;
import com.ecommerce.repository.repo.CartRepository;
import com.ecommerce.repository.repo.InventoryRepository;
import com.ecommerce.util.sqlutils.SqlQueryUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class CartRepositoryImpl implements CartRepository {

    @Autowired
    JdbcTemplate template;

    @Autowired
    InventoryRepository inventoryRepository;


    @Override
    public int getTotalCart(String cartId) {
        int total = 0;
        String query = "SELECT SUM(p.price) FROM cartItems ci , product p WHERE ci.cartId= " + cartId + " and ci.prodId =p.id";
        log.info("-------query: " + query + "-------");

        try {
            total = template.queryForObject(query, Integer.class);
            log.info("-------result : " + total + "-------");

        } catch (Exception e) {
            log.error("-------" + e + "-------");
        }
        return total;
    }


    @Override
    public int getLastProductQty(String id) {
        int total = 0;
        String query = "SELECT SUM(ci.qty) FROM cartItems ci WHERE ci.id= " + id;
        try {
            total = template.queryForObject(query, Integer.class);
        } catch (Exception e) {
            log.error("-------" + e + "-------");
        }
        return total;
    }

    @Override
    public int getIdOfProduct(String cartId, String productId) {
        int total = 0;
        String query = "SELECT id FROM cartItems where prodId=" + productId + " and cartId=" + cartId;
        try {
            total = template.queryForObject(query, Integer.class);
        } catch (Exception e) {
            log.error("-------" + e + "-------");
        }
        return total;
    }

    @Override
    public int getCartId(String userId) {
        int cartId = 0;
        String query = "SELECT id FROM cart WHERE cart.userId = " + userId + " LIMIT 1";
        log.info("-------query: " + query + "-------");

        try {
            cartId = template.queryForObject(query, Integer.class);
        } catch (Exception e) {
            log.error("-------" + e + "-------");
        }
        return cartId;
    }

    @Override
    public boolean isValidCart(String cartId) throws Exception {
        log.info("-------isValidCart()-------");
        String query = "SELECT COUNT(*) FROM cart where id=" + cartId;
        log.info("-------query: " + query + "-------");
        try {
            int result = template.queryForObject(query, Integer.class);
            if (result > 0) {
                log.info("-------cart Found: true -------");
                return true;
            } else {
                log.info("-------cart Found: false -------");
                return false;
            }
        } catch (Exception e) {
            log.info("-------Exception: " + e.getMessage() + "-------");
        }

        return false;
    }

    @Override
    public boolean isProductExistInCart(String cartId, String productId) throws Exception {
        log.info("-------isProductExistInCart()-------");
        String query = "SELECT COUNT(*) FROM cartItems where prodId=" + productId + " and cartId=" + cartId;
        log.info("-------query: " + query + "-------");
        try {
            int result = template.queryForObject(query, Integer.class);
            if (result > 0) {
                log.info("-------product Found: true -------");
                return true;
            } else {
                log.info("-------product Found: false -------");
                return false;
            }
        } catch (Exception e) {
            log.info("-------Exception: " + e.getMessage() + "-------");
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean updateCartQty(String action, String userId, String productId, String cartId, int quantity) throws Exception {
        boolean isCartExist = isValidCart(cartId);
        Boolean cartModel = new Boolean();

        if (isCartExist) {
            boolean isProductExist = isProductExistInCart(cartId, productId);
            if (isProductExist) {
                int id = getIdOfProduct(cartId, productId);
                int lastQty = getLastProductQty(String.valueOf(id));
                if (action.equals("add")) {
                    lastQty = lastQty + quantity;
                } else {
                    if (lastQty > 1 && quantity < lastQty) {
                        lastQty = lastQty - quantity;
                    } else {
                        throw new Exception("Invalid qty");
                    }
                }

                String query = "UPDATE " + SqlQueryUtils.TABLE_CART_ITEMS.TABLE_NAME + " SET"
                        + " " + SqlQueryUtils.TABLE_CART_ITEMS.qty + "=" + (lastQty)
                        + " WHERE prodId=" + productId + " and cartId=" + cartId + " and id=" + id;
                log.info("-------QUERY: ProductExist in cart" + query + "-------");

                int result = template.update(query);
                log.info("-------result: " + result + "-------");
                if (result > 0) {
                    cartModel.setCartId(Integer.parseInt(cartId));
                } else {
                    throw new Exception("Add to cart failed");
                }
            } else {
                log.info("-------QUERY: add new item to cart" + SqlQueryUtils.INSERT_INTO_CART + "-------");
                int result = template.update(SqlQueryUtils.INSERT_INTO_CART, new Object[]{cartId, productId, quantity});
                log.info("-------result: " + result + "-------");
                if (result > 0) {
                    cartModel.setCartId(Integer.parseInt(cartId));
                } else {
                    throw new Exception("Add to cart failed");
                }
            }
            cartModel.setCartId(Integer.parseInt(cartId));
//            int result = template.update(SqlQueryUtils.INSERT_INTO_CART, new Object[]{newCartId, userId, quantity});

        } else {
            //create a cart and then add this item to cart
            //INSERT INTO cart (userId,total) VALUES(9,0)
            log.info("-------query: " + SqlQueryUtils.CREATE_CART + "-------");
            try {
                int createCartResult = template.update(SqlQueryUtils.CREATE_CART, new Object[]{Integer.parseInt(userId), 0});
                log.info("-------query: " + SqlQueryUtils.INSERT_INTO_CART + "-------");
                int freshCartId = getCartId(userId);
                int result = template.update(SqlQueryUtils.INSERT_INTO_CART, new Object[]{freshCartId, productId, quantity});
                log.info("-------newCartId: " + createCartResult + "-------");
                log.info("-------result: " + result + "-------");

                if (result > 0 && createCartResult > 0) {
                    cartModel.setCartId(freshCartId);
                } else {
                    throw new Exception("Not implemented exception");
                }
            } catch (Exception e) {
                log.info("-------e: " + e.getMessage() + "-------");

                throw e;
            }
        }
        cartModel.setProductId(Integer.parseInt(productId));
        cartModel.setQty(getTotalCart(cartId));
        return cartModel;
    }

    @Override
    public int createCart() throws Exception {
        return 0;
    }

    @Override
    public int addToCart() throws Exception {
        return 0;
    }

    @Override
    public boolean deleteFromCart(String productId, String cartId, int quantity) throws Exception {
        log.info("-------deleteFromCart() -------");
//        log.info("" + e.getMessage() + "-------");
        if (!isValidCart(cartId)) {
            throw new Exception("Invalid cart id");
        }else if (getIdOfProduct(cartId,productId)==0){
            throw new Exception("Product not exist in cart");
        }
        String query = "DELETE FROM cartItems ci WHERE ci.prodId ="+productId+" AND ci.cartId ="+cartId;
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("cartId", cartId);
//        paramMap.put("productId", productId);
        log.info("-------query: " + query + "-------");
        try {
            int result = template.update(query);
            log.info("-------result: " + result + "-------");

            if (result == 1) {
                return true;
            } else {
                throw new Exception("Item not removed");
            }
        } catch (Exception e) {
            log.error("-------" + e + "-------");
            throw new Exception(e.getMessage());
        }

     }

    @Override
    public InventoryProduct getProducts(String cartId) throws Exception {
        return inventoryRepository.getProductsOfABasket(cartId);
    }

    @Override
    public boolean applyCoupon(String couponId, String cartId) throws Exception {
        return false;
    }

    @Override
    public List<Coupon> getCoupons(String userId, String cartId) throws Exception {
        return null;
    }

    @Override
    public boolean deleteCart(String cartId) throws Exception {
        return false;
    }
}
