package com.ecommerce.repository.repo;

import com.ecommerce.model.response.auth.LoginRequest;
import com.ecommerce.model.response.user.JavaAuthTokenResponse;

public interface AuthRepository {

    JavaAuthTokenResponse login(LoginRequest loginRequest);

    String getAuthToken(String userId);
}
