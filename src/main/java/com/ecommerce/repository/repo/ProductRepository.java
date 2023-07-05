package com.ecommerce.repository.repo;

import com.ecommerce.model.Category;
import com.ecommerce.model.response.product.InventoryProduct;
import com.ecommerce.model.response.product.Product;

import java.util.List;

public interface ProductRepository {

    Product getProductDetail(String productId, String cartId, String userId) throws Exception;

    InventoryProduct getProducts(String categoryId, String searchQuery, String userId, String cartId, int page);

    List<Category> getCategory() throws Exception;

    List<Category> getSubCategory(String categoryId) throws Exception;

}
