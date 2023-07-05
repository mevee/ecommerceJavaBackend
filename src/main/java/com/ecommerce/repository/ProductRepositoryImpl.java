package com.ecommerce.repository;

import com.ecommerce.model.Category;
import com.ecommerce.model.response.product.InventoryProduct;
import com.ecommerce.model.response.product.Product;
import com.ecommerce.repository.repo.InventoryRepository;
import com.ecommerce.repository.repo.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Product getProductDetail(String productId, String cartId, String userId) throws Exception {
        return inventoryRepository.getProductDetail(productId, cartId, userId);
    }

    @Override
    public InventoryProduct getProducts(String categoryId, String searchQuery, String userId, String cartId, int page) {
        log.info("----- getProducts() -------------");
        return inventoryRepository.getProducts(categoryId, searchQuery, userId, cartId, page);
    }

    @Override
    public List<Category> getCategory() {
        try {
            return inventoryRepository.getCategory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> getSubCategory(String categoryId) throws Exception {

        return inventoryRepository.getSubCategory(categoryId);
    }
}
