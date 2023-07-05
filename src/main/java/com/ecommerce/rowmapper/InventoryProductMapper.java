package com.ecommerce.rowmapper;

import com.ecommerce.model.response.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j

public class InventoryProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product category = new Product();
        if (rs != null) {
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            category.setDescription(rs.getString("description"));
            category.setDescription(rs.getString("description"));
            category.setPrice(rs.getInt("price"));
            category.setInventoryQty(rs.getInt("inventoryQty"));
         }
        return category;
    }

}
