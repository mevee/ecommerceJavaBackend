/*
 *
 *  * *
 *  *  * Copyright (C) Addverb - All Rights Reserved
 *  *  * Unauthorized copying of this file, via any medium is strictly prohibited
 *  *  * Proprietary and confidential
 *  *
 *
 */
package com.ecommerce.rowmapper;

import com.ecommerce.model.response.user.JavaAuthTokenResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author abhishek on 28/01/20.
 */
public class UserMapper implements RowMapper<JavaAuthTokenResponse> {

    @Override
    public JavaAuthTokenResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        JavaAuthTokenResponse user = new JavaAuthTokenResponse();
        user.setId(rs.getInt("id"));
        user.setEmail(rs.getString("email"));
        user.setAuthToken(rs.getString("authToken"));
        user.setPhone(rs.getString("phone"));
        user.setfName(rs.getString("fName"));
        user.setlName(rs.getString("lName"));
        user.setCountryCode(rs.getString("countryCode"));
        user.setIsVerified(rs.getString("isVerified"));
        user.setRoleId(rs.getString("roleId"));
//        user.setCreatedAt(rs.getString("createdAt"));
        return user;
    }


}
