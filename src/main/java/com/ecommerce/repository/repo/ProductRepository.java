package com.ecommerce.repository.repo;

import com.ecommerce.model.Category;
import com.ecommerce.model.request.product.AddProductRequest;
import com.ecommerce.model.response.product.InventoryProduct;
import com.ecommerce.model.response.product.Product;
import com.ecommerce.model.sku.Sku;

import java.util.List;

public interface ProductRepository {

    Product getProductDetail(String productId, String cartId, String userId);

    InventoryProduct getProducts(String categoryId, String searchQuery, String userId, String cartId, int page);


    List<Category> getCategory() throws Exception;

    List<Category> getSubCategory(String categoryId) throws Exception;

    boolean isValidCategory(String categoryId);

    boolean addSku(Sku sku) throws Exception;

    List<Sku> getAllSku() throws Exception;


    boolean addProduct(AddProductRequest product) throws Exception;

    boolean isValidProduct(int skuId) throws Exception;

}
