package com.ecommerce.service;

import com.ecommerce.model.response.product.InventoryProduct;
import com.ecommerce.model.response.product.Product;
import com.ecommerce.repository.repo.ProductRepository;
import com.ecommerce.service.interfaces.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository repository;


    @Override
    public InventoryProduct loadAllProduct(String catId, String searchQuery, String userId, String cartId, int page) throws Exception {

        return repository.getProducts(catId, searchQuery, userId, cartId, page);
    }

    @Override
    public Product productDetail(String productId, String catId, String userId) throws Exception {
        return repository.getProductDetail(productId,catId,userId);
     }
}
