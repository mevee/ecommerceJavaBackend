package com.ecommerce.controller;

import com.ecommerce.model.Coupon;
import com.ecommerce.model.GenericResponse;
import com.ecommerce.model.cart.Boolean;
import com.ecommerce.model.cart.Cart;
import com.ecommerce.model.request.cart.CartUpdateRequest;
import com.ecommerce.model.request.cart.GetCartDetailRequest;
import com.ecommerce.service.interfaces.CartService;
import com.ecommerce.util.AppConstants;
import com.ecommerce.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/cart-detail")
    ResponseEntity<GenericResponse> getCartDetail(@RequestBody GetCartDetailRequest request) {

        GenericResponse response = CommonUtils.getSuccessResponse(null);

        System.out.println("---------------Check getCartDetail request---------------");
        log.info("-----REQUEST " + request);

        if (request == null || request.getCartId() == 0 || request.getUserId() == 0) {
            response.getMeta().setMessageCode(AppConstants.ERR400);
            response.getMeta().setStatus(AppConstants.ERR400);
            response.getMeta().setMessageDescription(AppConstants.BAD_CREDENTIALS);
        } else {
            try {
                Cart cart = cartService.getCart(request);
                response.setData(cart);
                response.getMeta().setStatus(AppConstants.SUCCESS);
                response.getMeta().setMessageCode(AppConstants.SUCCESS_CODE);
                response.getMeta().setMessageDescription(AppConstants.SUCCESS_MSG);
            } catch (Exception e) {
                response.getMeta().setStatus(AppConstants.ERR400);
                response.getMeta().setMessageCode(AppConstants.ERR400);
                response.getMeta().setMessageDescription(e.getMessage());
            }

        }

        System.out.println("------------return bad request or bad parameter response--------------");
        System.out.println("------------" + response.toString() + "------------");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/update-to-cart")
    ResponseEntity<GenericResponse> addToCart(@RequestBody CartUpdateRequest request) {
        System.out.println("---------------Check getCartDetail() request---------------");
        System.out.println("---------------" + request + "---------------");

        GenericResponse response = CommonUtils.getSuccessResponse(null);
        Map<String, Object> data = new HashMap();
        if (request == null || request.getUserId() == 0) {
            return new ResponseEntity<>(CommonUtils.error(response, "Invalid user id"), HttpStatus.OK);
        } else if (request.getProdId() <= 0) {
            return new ResponseEntity<>(CommonUtils.error(response, "Invalid product id"), HttpStatus.OK);
        } else if (request.getQty() <= 0) {
            return new ResponseEntity<>(CommonUtils.error(response, "Invalid Quantity"), HttpStatus.OK);
        } else if (request.getAction() == null || request.getAction().isEmpty()) {
            return new ResponseEntity<>(CommonUtils.error(response, "Invalid action"), HttpStatus.OK);
        }
        try {

            Boolean updateCartModel = cartService.updateCartQty(request.getAction(), "" + request.getUserId(), "" + request.getProdId(), "" + request.getCartId(), request.getQty());
            data.put("cartId", updateCartModel.getCartId());
            data.put("qty", String.valueOf(updateCartModel.getQty()));
            response.setData(data);
        } catch (Exception e) {
            log.info("----------e:" + e.getMessage() + "------------");
            response = CommonUtils.error(response, e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/remove-from-cart")
    ResponseEntity<GenericResponse> removeFromCart(@RequestBody CartUpdateRequest request) {
        GenericResponse response = CommonUtils.getSuccessResponse(null);
        if (request == null || request.getCartId() == 0 || request.getUserId() == 0) {
            return new ResponseEntity<>(CommonUtils.paramMissing(response), HttpStatus.OK);
        }
        try {
            cartService.removeFromCart("" + request.getProdId(), "" + request.getCartId(), request.getQty());
        } catch (Exception e) {
            response = CommonUtils.error(response, e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/delete-cart-item")
    ResponseEntity<GenericResponse> deleteCartItem(@RequestBody CartUpdateRequest request) {
        System.out.println("---------------deleteCartQty()---------------");
        System.out.println("---------------" + request + "---------------");

        GenericResponse response = CommonUtils.getSuccessResponse(null);
        if (request == null || request.getCartId() == 0 || request.getUserId() == 0) {
            return new ResponseEntity<>(CommonUtils.paramMissing(response), HttpStatus.OK);
        }
        try {
            cartService.removeFromCart("" + request.getProdId(), "" + request.getCartId(), 0);
        } catch (Exception e) {
            response = CommonUtils.error(response, e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/apply-coupon")
    ResponseEntity<GenericResponse> applyCoupon(@RequestBody Coupon coupon) {
        GenericResponse response = CommonUtils.getSuccessResponse(null);
        try {
//            List<Category> category = inventoryService.getCategory();
//            response.setData(category);
        } catch (Exception e) {
            response.setData(null);
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

}
