package com.example.todoapp.service.interfaces;

import com.example.todoapp.model.response.auth.LoginRequest;
import com.example.todoapp.model.response.user.JavaAuthTokenResponse;


public interface AuthService {
    JavaAuthTokenResponse loginWithEmailAndPassword(LoginRequest request);

    JavaAuthTokenResponse getUser(JavaAuthTokenResponse user);

}
