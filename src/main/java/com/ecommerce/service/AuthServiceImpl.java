package com.ecommerce.service;

import com.ecommerce.service.interfaces.AuthService;
import com.ecommerce.model.response.auth.LoginRequest;
import com.ecommerce.model.response.user.JavaAuthTokenResponse;
import com.ecommerce.repository.repo.AuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.apache.log4j.LogMF.log;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public JavaAuthTokenResponse loginWithEmailAndPassword(LoginRequest request) {
        JavaAuthTokenResponse user =  authRepository.login(request);
         log.info("-------- loginWithEmailAndPassword()----------------"+user);

        return user;
    }

    @Override
    public JavaAuthTokenResponse getUser(JavaAuthTokenResponse user) {
        return null;
    }
}
