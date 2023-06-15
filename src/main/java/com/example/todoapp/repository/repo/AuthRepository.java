package com.example.todoapp.repository.repo;

import com.example.todoapp.model.response.auth.LoginRequest;
import com.example.todoapp.model.response.user.JavaAuthTokenResponse;

public interface AuthRepository {

    JavaAuthTokenResponse login(LoginRequest loginRequest);

    String getAuthToken(String userId);
}
