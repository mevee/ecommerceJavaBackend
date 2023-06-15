package com.example.todoapp.repository.repo;

import com.example.todoapp.model.Category;
import com.example.todoapp.model.response.product.Product;

import java.util.List;

public interface CartRepository {
/*Cart Story
* create a cart
* delete a cart
* contains all the product items
* cart total value sum of all product selling at
* cart discounted value overall discount
* offer applied or apply offer (Not sure)
* totalSavings
* userAddress shipping and billing address
* add to cart
* remove from cart
* update a cart quantity
*
* */
    Product getProductDetail(String productId, String cartId, String userId);

    List<Product> getProducts(String categoryId, String searchQuery, String userId, String cartId);

    List<Category> getCategory() throws Exception;

    List<Category> getSubCategory(String categoryId) throws Exception;

}
