package com.ecommerce.service;

import com.ecommerce.service.interfaces.UserService;
import com.ecommerce.model.request.user.PasswordChangeRequest;
import com.ecommerce.model.response.user.JavaAuthTokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public JavaAuthTokenResponse saveUser(JavaAuthTokenResponse user) {
        return null;
    }

    @Override
    public JavaAuthTokenResponse updateUser(JavaAuthTokenResponse user) {
        return null;
    }

    @Override
    public List<JavaAuthTokenResponse> loadAllRegisterUser(JavaAuthTokenResponse user) {
        return null;
    }

    @Override
    public JavaAuthTokenResponse removeUser(JavaAuthTokenResponse user) {
        return null;
    }

    @Override
    public JavaAuthTokenResponse disableUser(JavaAuthTokenResponse user) {
        return null;
    }

    @Override
    public JavaAuthTokenResponse enableUser(JavaAuthTokenResponse user) {
        return null;
    }

    @Override
    public JavaAuthTokenResponse changePassword(PasswordChangeRequest user) {
        return null;
    }

    @Override
    public JavaAuthTokenResponse updateProfileImage() {
        return null;
    }
}
