package com.ecommerce.controller;

import com.ecommerce.model.response.auth.LoginRequest;
import com.ecommerce.model.response.user.JavaAuthTokenResponse;
import com.ecommerce.service.interfaces.AuthService;
import com.ecommerce.util.AppConstants;
import com.ecommerce.util.CommonUtils;
import com.ecommerce.model.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    ResponseEntity<GenericResponse> login(@RequestBody LoginRequest request) {

        GenericResponse response = CommonUtils.getSuccessResponse(null);
        if (request == null || request.getEmail() == null) {
            response.getMeta().setMessageCode(AppConstants.ERR400);
            response.getMeta().setMessageDescription(AppConstants.BAD_CREDENTIALS);
        } else if (request.getPassword() == null) {
            response.getMeta().setMessageCode(AppConstants.ERR400);
            response.getMeta().setMessageDescription(AppConstants.BAD_CREDENTIALS);
        } else {
            JavaAuthTokenResponse response1 = null;
            try {
                response1 = authService.loginWithEmailAndPassword(request);

            } catch (Exception e) {
                log.error(e.getMessage());
                response.getMeta().setStatus(AppConstants.INVALID_REQUEST_ERR_CODE);
                response.getMeta().setMessageCode(AppConstants.INVALID_REQUEST_ERR_CODE);
                response.getMeta().setMessageDescription(AppConstants.BAD_CREDENTIALS);
              }

            System.out.println(response1);

            response.setData(response1);
        }

        System.out.println("------------return bad request or bad parameter response--------------");
        System.out.println("------------" + response.toString() + "------------");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


}
