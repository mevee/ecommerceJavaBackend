package com.ecommerce.service.interfaces;

import com.ecommerce.model.response.product.InventoryProduct;
import com.ecommerce.model.response.product.Product;

import java.util.List;

public interface ProductService {
    InventoryProduct loadAllProduct(String catId, String searchQuery, String userId, String cartId, int page) throws Exception;

    Product productDetail(String productId,String userId, String cartId) throws Exception;

}
