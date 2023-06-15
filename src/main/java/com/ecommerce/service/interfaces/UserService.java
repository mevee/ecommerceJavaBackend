package com.ecommerce.service.interfaces;

import com.ecommerce.model.request.user.PasswordChangeRequest;
import com.ecommerce.model.response.user.JavaAuthTokenResponse;

import java.util.List;
public interface UserService {
    JavaAuthTokenResponse saveUser(JavaAuthTokenResponse user);
    JavaAuthTokenResponse updateUser(JavaAuthTokenResponse user);
    List<JavaAuthTokenResponse> loadAllRegisterUser(JavaAuthTokenResponse user);
    JavaAuthTokenResponse removeUser(JavaAuthTokenResponse user);
    JavaAuthTokenResponse disableUser(JavaAuthTokenResponse user);
    JavaAuthTokenResponse enableUser(JavaAuthTokenResponse user);
    JavaAuthTokenResponse changePassword(PasswordChangeRequest user);
    JavaAuthTokenResponse updateProfileImage();

}
