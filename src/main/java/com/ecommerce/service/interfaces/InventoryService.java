package com.ecommerce.service.interfaces;

import com.ecommerce.model.Category;
import com.ecommerce.model.request.product.AddProductRequest;
import com.ecommerce.model.response.product.InventoryProduct;
import com.ecommerce.model.sku.ProductImage;
import com.ecommerce.model.sku.Sku;

import java.util.List;

public interface InventoryService {
    boolean addCategory(Category category) throws Exception;

    boolean removeCategory(Category category) throws Exception;

    List<Category> getCategory()throws Exception;

    List<Category> getSubCategory(String categoryId)throws Exception;

    boolean removeSku(Sku sku)throws Exception;

    boolean updateSku(Sku sku) throws Exception;

    List<Sku> getAllSku() throws Exception;

    boolean updateProduct(AddProductRequest product) throws Exception;

    boolean removeProduct(AddProductRequest inventoryProduct) throws Exception;

    boolean updateProductImage(ProductImage product) throws Exception;

    boolean removeProductImage(ProductImage inventoryProduct) throws Exception;
    List<ProductImage> getProductImage(String productId) throws Exception;

    InventoryProduct loadAllProduct(String catId, String searchQuery, String userId, String cartId, int page)throws Exception;
}
