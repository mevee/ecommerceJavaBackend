package com.example.todoapp.repository.repo;

import com.example.todoapp.model.Category;
import com.example.todoapp.model.request.product.AddProductRequest;
import com.example.todoapp.model.request.product.ProductQueryRequest;
import com.example.todoapp.model.response.auth.LoginRequest;
import com.example.todoapp.model.response.product.Product;
import com.example.todoapp.model.response.user.JavaAuthTokenResponse;
import com.example.todoapp.model.sku.Sku;

import java.util.List;

public interface ProductRepository {

    Product getProductDetail(String productId, String cartId, String userId);

    List<Product> getProducts(String categoryId, String searchQuery, String userId, String cartId,int page);


    List<Category> getCategory() throws Exception;

    List<Category> getSubCategory(String categoryId) throws Exception;
    boolean isValidCategory(String categoryId);

    boolean addSku(Sku sku) throws Exception;

    List<Sku> getAllSku() throws Exception;


    boolean addProduct(AddProductRequest product) throws Exception;
    boolean isValidProduct(int skuId) throws Exception;

}
