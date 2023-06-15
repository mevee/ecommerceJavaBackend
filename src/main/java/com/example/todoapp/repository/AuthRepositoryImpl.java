package com.example.todoapp.repository;

import com.example.todoapp.model.response.auth.LoginRequest;
import com.example.todoapp.model.response.user.JavaAuthTokenResponse;
import com.example.todoapp.repository.repo.AuthRepository;
import com.example.todoapp.rowmapper.UserMapper;
import com.example.todoapp.util.DbInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class AuthRepositoryImpl implements AuthRepository {
    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public JavaAuthTokenResponse login(LoginRequest loginRequest) {
        //        String query = "SELECT COUNT(*) FROM " + DbInfo.User.TABLE_NAME + " WHERE email = ?";

        String query = "SELECT * FROM " + DbInfo.User.TABLE_NAME + " WHERE email=? and password=?";
        log.info("--------Query " + query + " ----");

        JavaAuthTokenResponse user = jdbc.queryForObject(query, new Object[]{loginRequest.getEmail(),loginRequest.getPassword()}, new UserMapper());
        log.info("--------Query RESULT" + user + " ----");

        return user;
    }

    @Override
    public String getAuthToken(String userId) {
        return null;
    }

    /*UserRequest login(LoginRequest loginRequest){
        UserRequest user = null;


        return  user;
    }
    boolean saveUser(UserRequest user){
        boolean isSaved=false;
        //do db operation and save user to db

        return isSaved;
    }

    String getAuthToken(String userId){
        String token="";
        //db.getAuthTokenBasedOn userId
        return token;
    }*/


}
