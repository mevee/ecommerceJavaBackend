package com.example.todoapp.rowmapper;

import com.example.todoapp.model.Category;
import com.example.todoapp.model.sku.Sku;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkuListMapper implements RowMapper<List<Sku>> {
    @Override
    public List<Sku> mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<Sku> results = new ArrayList();
        do {
            if (rs!=null){
                Sku sku = new Sku();
                sku.setId(rs.getInt("id"));
                sku.setName(rs.getString("name"));
                sku.setDescription(rs.getString("description"));
                sku.setState(rs.getString("state"));
                sku.setWeight(rs.getInt("weight"));
                sku.setUnit(rs.getInt("unit"));
                sku.setHighValue(rs.getString("highValue"));
                sku.setLiquidIndicator(rs.getString("liquidIndicator"));
                results.add(sku);

            }
        }while (rs.next());

        return results;
    }
}
