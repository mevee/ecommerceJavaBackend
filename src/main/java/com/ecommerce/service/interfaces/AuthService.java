package com.ecommerce.service.interfaces;

import com.ecommerce.model.response.auth.LoginRequest;
import com.ecommerce.model.response.user.JavaAuthTokenResponse;


public interface AuthService {
    JavaAuthTokenResponse loginWithEmailAndPassword(LoginRequest request);

    JavaAuthTokenResponse getUser(JavaAuthTokenResponse user);

}
