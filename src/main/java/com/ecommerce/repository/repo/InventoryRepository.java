package com.ecommerce.repository.repo;

import com.ecommerce.model.Category;
import com.ecommerce.model.request.product.AddProductRequest;
import com.ecommerce.model.response.product.InventoryProduct;
import com.ecommerce.model.response.product.Product;
import com.ecommerce.model.sku.ProductImage;
import com.ecommerce.model.sku.Sku;

import java.util.List;

public interface InventoryRepository {

    boolean isValidCategory(String categoryId);

    List<Category> getCategory() throws Exception;

    List<Category> getSubCategory(String categoryId) throws Exception;

    boolean updateCategory(Category category) throws Exception;

    boolean removeCategory(Category sku) throws Exception;

    boolean addSku(Sku sku) throws Exception;

    boolean removeSku(Sku sku) throws Exception;

    List<Sku> getAllSku() throws Exception;

    boolean addProduct(AddProductRequest product) throws Exception;

    boolean isValidProduct(int skuId) throws Exception;

    Product getProductDetail(String productId, String cartId, String userId) throws Exception;

    boolean updateProductImage(ProductImage product) throws Exception;

    boolean removeProductImage(ProductImage inventoryProduct) throws Exception;
    List<ProductImage> getProductImage(String productId) throws Exception;
    InventoryProduct getProducts(String categoryId, String searchQuery, String userId, String cartId, int page);
    InventoryProduct getProductsOfABasket(String cartId);
}
