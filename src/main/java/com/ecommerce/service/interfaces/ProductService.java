package com.ecommerce.service.interfaces;

import com.ecommerce.model.response.product.InventoryProduct;

import java.util.List;

public interface ProductService {
    InventoryProduct loadAllProduct(String catId, String searchQuery, String userId, String cartId, int page) throws Exception;


}
