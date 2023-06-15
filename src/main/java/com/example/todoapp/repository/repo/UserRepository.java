package com.example.todoapp.repository.repo;

import com.example.todoapp.model.response.user.JavaAuthTokenResponse;

import java.util.List;

public interface UserRepository {
    Boolean isUserExist(String userID) throws Exception;
    Boolean isEmailExist(String email) throws Exception;

    Boolean isPhoneExist(String phone) throws Exception;

    Boolean register(JavaAuthTokenResponse request) throws Exception;

    JavaAuthTokenResponse updateUser(JavaAuthTokenResponse request) throws Exception;

    JavaAuthTokenResponse enable(JavaAuthTokenResponse request, Boolean enable) throws Exception;

    JavaAuthTokenResponse deleteUser(JavaAuthTokenResponse request, Boolean enable) throws Exception;

    List<JavaAuthTokenResponse> loadAllUsers() throws Exception;


}
