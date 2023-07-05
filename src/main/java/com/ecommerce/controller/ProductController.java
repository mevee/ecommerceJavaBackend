package com.ecommerce.controller;

import com.ecommerce.model.Category;
import com.ecommerce.model.GenericResponse;
import com.ecommerce.model.request.product.ProductDetailRequest;
import com.ecommerce.model.request.product.ProductQueryRequest;
import com.ecommerce.model.response.product.InventoryProduct;
import com.ecommerce.model.response.product.Product;
import com.ecommerce.service.interfaces.InventoryService;
import com.ecommerce.service.interfaces.ProductService;
import com.ecommerce.util.AppConstants;
import com.ecommerce.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/detail")
    ResponseEntity<GenericResponse> getProductDetails(@RequestBody ProductDetailRequest request) {
        log.info("---------------getProductDetails()---------------");
        log.info("---------------REQUEST " + request + "---------------");
        GenericResponse response = CommonUtils.getSuccessResponse(null);

        if (request == null || request.getProductId() == null || request.getUserId() == null) {
            response.getMeta().setMessageCode(AppConstants.ERR400);
            response.getMeta().setStatus(AppConstants.ERR400);
            response.getMeta().setMessageDescription(AppConstants.BAD_CREDENTIALS);
        } else {
            try {
                Product product = productService.productDetail(request.getProductId(), request.getUserId(), request.getCartId());
                response.setData(product);
            } catch (Exception e) {
                response.getMeta().setMessageDescription(e.getMessage());
                response.getMeta().setStatus(AppConstants.ERR400);
                response.getMeta().setMessageCode(AppConstants.ERR400);
            }
        }

        System.out.println("------------return bad request or bad parameter response--------------");
        System.out.println("------------" + response.toString() + "------------");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/category")
    ResponseEntity<GenericResponse> getCategories() {
        GenericResponse response = CommonUtils.getSuccessResponse(null);
        try {
            List<Category> category = inventoryService.getCategory();
            response.setData(category);
        } catch (Exception e) {
            response.setData(null);
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    ResponseEntity<GenericResponse> getCategories(@PathVariable("id") String categoryId) {
        GenericResponse response = CommonUtils.getSuccessResponse(null);
        try {
            List<Category> category = inventoryService.getSubCategory(categoryId);
            response.setData(category);
        } catch (Exception e) {
            response.getMeta().setMessageDescription(e.getMessage());
            response.setData(null);
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/get-products")
    ResponseEntity<GenericResponse> getProducts(@RequestBody ProductQueryRequest request) {
        log.info("----------------getProducts() request :" + request + " --------");
        GenericResponse response = CommonUtils.getSuccessResponse(null);
        try {
            InventoryProduct inventoryProduct = productService.loadAllProduct(request.getCategoryId(), request.getSearchQuery(), request.getUserId(), request.getCartId(), request.getPage());
            response.setData(inventoryProduct);
            response.getMeta().setStatus(AppConstants.SUCCESS);
            response.getMeta().setMessageCode(AppConstants.SUCCESS_CODE);
            response.getMeta().setMessageDescription(AppConstants.SUCCESS_MSG);
        } catch (Exception e) {
            log.info("---------------- e.getMessage() :" + e.getMessage() + " --------");
            response.getMeta().setStatus("Error");
            response.getMeta().setMessageCode("Error");
            response.getMeta().setStatus(AppConstants.ERR400);
            response.getMeta().setMessageCode(AppConstants.ERR400);
            response.getMeta().setMessageDescription(e.getMessage());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
