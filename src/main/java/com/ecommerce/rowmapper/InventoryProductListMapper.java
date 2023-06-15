package com.ecommerce.rowmapper;

import com.ecommerce.model.response.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j

public class InventoryProductListMapper implements RowMapper<List<Product>> {
    @Override
    public List<Product> mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<Product> results = new ArrayList();
        do {
            if (rs != null) {
                Product category = new Product();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                category.setDescription(rs.getString("description"));
                category.setPrice(rs.getInt("price"));
                category.setInventoryQty(rs.getInt("inventoryQty"));
                results.add(category);
            }
        } while (rs.next());

        return results;
    }

}
