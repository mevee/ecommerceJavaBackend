package com.example.todoapp.repository;

import com.example.todoapp.model.response.user.JavaAuthTokenResponse;
import com.example.todoapp.repository.repo.UserRepository;
import com.example.todoapp.util.DbInfo;
import com.example.todoapp.util.sqlutils.SqlQueryUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbc;
//    @Autowired
//    private PasswordEncoder passEncoder;

    @Override
    public Boolean isUserExist(String userID) throws Exception {
        String query = "SELECT COUNT(*) FROM " + DbInfo.User.TABLE_NAME + " WHERE id = ?";
        int userId = -1;
        try {
            userId = Integer.parseInt(userID);
        } catch (NumberFormatException exception) {

        }
        if (userId <= 0) {
            throw new Exception("User id not found");
        }

        log.info("-------- Is Exists By userId Query----------------");
        log.info(query);
        Integer count = jdbc.queryForObject(query, new Object[]{userId}, Integer.class);
        log.info("----is Exists By userId count: " + (count) + " -------------");
        return count != null && count > 0;
    }

    @Override
    public Boolean isEmailExist(String email) throws Exception {
        String query = "SELECT COUNT(*) FROM " + DbInfo.User.TABLE_NAME + " WHERE email = ?";

        if (email == null || email.isEmpty()) {
            throw new Exception("User email not found");
        }

        log.info("-------- Is Exists By email Query----------------");
        log.info(query);
        Integer count = jdbc.queryForObject(query, new Object[]{email}, Integer.class);
        log.info("----is Exists By email count: " + (count) + " -------------");
        return count != null && count > 0;
    }
    @Override
    public Boolean isPhoneExist(String phone) throws Exception {
        String query = "SELECT COUNT(*) FROM " + DbInfo.User.TABLE_NAME + " WHERE phone = ?";

        if (phone == null || phone.isEmpty()) {
            throw new Exception("User phone not found");
        }

        log.info("-------- Is Exists By phone Query----------------");
        log.info(query);
        Integer count = jdbc.queryForObject(query, new Object[]{phone}, Integer.class);
        log.info("----is Exists By phone count: " + (count) + " -------------");
        return count != null && count > 0;
    }
    @Override
    public Boolean register(JavaAuthTokenResponse request) throws Exception {
        log.info("--------------------- register() ----------------");

        JavaAuthTokenResponse response = new JavaAuthTokenResponse();
        if (request.getEmail() == null) {
            throw new Exception("Invalid email");
        } else if (isEmailExist(request.getEmail())) {
            throw new Exception("User with given email already exist");
        }else if (isPhoneExist(request.getPhone())) {
            throw new Exception("User with given phone already exist");
        } else {
            log.info(SqlQueryUtils.REGISTER_USER);
            int count = jdbc.update(SqlQueryUtils.REGISTER_USER,
                    new Object[]{request.getfName(),
                    request.getlName(),
                    request.getEmail(),
                    request.getPhone(),
                    request.getCountryCode(),
                    request.getRoleId(),
                    request.getPassword()});
            log.info("---- Save User Data Detail count: " + (count) + " -------------");
            if (count >= 0) {
                log.info("--------------------- Save User Data Detail inserted successfully ----------------");
                return true;
            } else {
                log.error("--------------------- Save User Data Detail inserted failed ----------------");
                return false;
            }
    }

}

    @Override
    public JavaAuthTokenResponse updateUser(JavaAuthTokenResponse request) throws Exception {
        return null;
    }

    @Override
    public JavaAuthTokenResponse enable(JavaAuthTokenResponse request, Boolean enable) throws Exception {
        return null;
    }

    @Override
    public JavaAuthTokenResponse deleteUser(JavaAuthTokenResponse request, Boolean enable) throws Exception {
        return null;
    }

    @Override
    public List<JavaAuthTokenResponse> loadAllUsers() throws Exception {
        return null;
    }
}
