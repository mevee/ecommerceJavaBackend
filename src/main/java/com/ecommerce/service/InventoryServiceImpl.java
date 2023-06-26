package com.ecommerce.service;

import com.ecommerce.model.Category;
import com.ecommerce.model.request.product.AddProductRequest;
import com.ecommerce.model.response.product.InventoryProduct;
import com.ecommerce.model.sku.ProductImage;
import com.ecommerce.model.sku.Sku;
import com.ecommerce.repository.repo.InventoryRepository;
import com.ecommerce.service.interfaces.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    InventoryRepository inventoryService;

    @Override
    public boolean addCategory(Category category) throws Exception {
        return inventoryService.updateCategory(category);
    }

    @Override
    public boolean removeCategory(Category category) throws Exception {
        return inventoryService.removeCategory(category);
    }

    @Override
    public List<Category> getCategory() throws Exception {
        return inventoryService.getCategory();
    }

    @Override
    public List<Category> getSubCategory(String categoryId) throws Exception {
        return inventoryService.getSubCategory(categoryId);
    }

    @Override
    public boolean removeSku(Sku sku) throws Exception {
        return inventoryService.removeSku(sku);
    }

    @Override
    public boolean updateSku(Sku sku) throws Exception {
        return inventoryService.addSku(sku);
    }

    @Override
    public List<Sku> getAllSku() throws Exception {
        return inventoryService.getAllSku();
    }

    @Override
    public boolean updateProduct(AddProductRequest product) throws Exception {
        return inventoryService.addProduct(product);
    }

    @Override
    public boolean removeProduct(AddProductRequest inventoryProduct) throws Exception {
        return false;
    }

    @Override
    public boolean updateProductImage(ProductImage product) throws Exception {
        return false;
    }

    @Override
    public boolean removeProductImage(ProductImage inventoryProduct) throws Exception {
        return false;
    }

    @Override
    public List<ProductImage> getProductImage(String productId) throws Exception {
        return null;
    }

    @Override
    public InventoryProduct loadAllProduct(String catId, String searchQuery, String userId, String cartId, int page) throws Exception {
        return inventoryService.getProducts(catId, searchQuery, userId, cartId, page);
    }


}
