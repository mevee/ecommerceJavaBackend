package com.example.todoapp.controller;

import com.example.todoapp.model.Category;
import com.example.todoapp.model.GenericResponse;
import com.example.todoapp.model.request.product.AddProductRequest;
import com.example.todoapp.model.request.product.ProductDetailRequest;
import com.example.todoapp.model.request.product.ProductQueryRequest;
import com.example.todoapp.model.response.product.Product;
import com.example.todoapp.model.sku.Sku;
import com.example.todoapp.repository.repo.ProductRepository;
import com.example.todoapp.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.todoapp.util.AppConstants.*;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/detail")
    ResponseEntity<GenericResponse> getProductDetails(@RequestBody ProductDetailRequest request) {

        GenericResponse response = CommonUtils.getSuccessResponse(null);

        System.out.println("---------------Check register request---------------");
        log.info("-----REQUEST " + request);

        if (request == null || request.getProductId() == null) {
            response.getMeta().setMessageCode(ERR400);
            response.getMeta().setStatus(ERR400);
            response.getMeta().setMessageDescription(BAD_CREDENTIALS);
        } else {
            boolean isSavedSuccess = false;
            try {
//                isSavedSuccess = productRepository.register(request);
            } catch (Exception e) {
                isSavedSuccess = false;
                response.getMeta().setStatus(ERR400);
                response.getMeta().setMessageCode(ERR400);
                response.getMeta().setMessageDescription(e.getMessage());
            }
            if (isSavedSuccess) {
                response.getMeta().setStatus(SUCCESS);
                response.getMeta().setMessageCode(SUCCESS_CODE);
                response.getMeta().setMessageDescription(SUCCESS_MSG);
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
            List<Category> category = productRepository.getCategory();
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
            List<Category> category = productRepository.getSubCategory(categoryId);
            response.setData(category);
        } catch (Exception e) {
            response.getMeta().setMessageDescription(e.getMessage());
            response.setData(null);
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/add-sku")
    ResponseEntity<GenericResponse> addSku(@RequestBody Sku categoryId) {
        GenericResponse response = CommonUtils.getSuccessResponse(null);
        try {
            boolean saved = productRepository.addSku(categoryId);
            if (saved) {
                response.getMeta().setStatus("Success");
            } else {
                response.getMeta().setStatus("Failed");
            }
        } catch (Exception e) {
            response.getMeta().setMessageDescription(e.getMessage());
            response.getMeta().setStatus("Failed");
            response.getMeta().setMessageCode("400");
            response.setData(null);
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/get-all-sku")
    ResponseEntity<GenericResponse> getAllCategory() {
        GenericResponse response = CommonUtils.getSuccessResponse(null);
        try {
            List<Sku> skuList = productRepository.getAllSku();
            response.setData(skuList);
            response.getMeta().setStatus(SUCCESS);
            response.getMeta().setMessageCode(SUCCESS_CODE);
            response.getMeta().setMessageDescription(SUCCESS_MSG);
        } catch (Exception e) {
            response.getMeta().setStatus("Error");
            response.getMeta().setMessageCode("Error");
            response.getMeta().setStatus(ERR400);
            response.getMeta().setMessageCode(ERR400);
            response.getMeta().setMessageDescription(e.getMessage());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add-product")
    ResponseEntity<GenericResponse> addProduct(@RequestBody AddProductRequest product) {
        GenericResponse response = CommonUtils.getSuccessResponse(null);
        log.info("----------addProduct()" + product + "---------");

        if (product == null) {
            return new ResponseEntity(CommonUtils.paramMissing(response), HttpStatus.OK);
        }/*else if (product.){

        }*/
        try {
            boolean saved = productRepository.addProduct(product);
            if (saved) {
                response.getMeta().setStatus("Success");
            } else {
                response.getMeta().setStatus("Failed");
                response.getMeta().setMessageCode("Failed to save");
            }
        } catch (Exception e) {
            response = CommonUtils.paramMissing(response);
            response.getMeta().setMessageDescription(e.getMessage());
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/get-products")
    ResponseEntity<GenericResponse> getProducts(@RequestBody ProductQueryRequest request) {
        log.info("----------------getProducts() request :" + request + " --------");
        GenericResponse response = CommonUtils.getSuccessResponse(null);
        try {
            List<Product> skuList = productRepository.getProducts(request.getCategoryId(), request.getSearchQuery(), request.getUserId(), request.getCartId(),request.getPage());
            response.setData(skuList);
            response.getMeta().setStatus(SUCCESS);
            response.getMeta().setMessageCode(SUCCESS_CODE);
            response.getMeta().setMessageDescription(SUCCESS_MSG);
        } catch (Exception e) {
            log.info("---------------- e.getMessage() :" + e.getMessage() + " --------");
            response.getMeta().setStatus("Error");
            response.getMeta().setMessageCode("Error");
            response.getMeta().setStatus(ERR400);
            response.getMeta().setMessageCode(ERR400);
            response.getMeta().setMessageDescription(e.getMessage());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
