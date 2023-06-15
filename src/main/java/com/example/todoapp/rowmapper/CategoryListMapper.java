package com.example.todoapp.rowmapper;

import com.example.todoapp.model.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j

public class CategoryListMapper implements RowMapper<List<Category>> {
    @Override
    public List<Category> mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<Category> results = new ArrayList();
        do {
            if (rs != null) {
                //            log.info("--------rs->" + rs.first() + " ----");
                log.info("--------Query RESULT at while obj->  ----");
                Category category = new Category();
                category.setId("" + rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                category.setSubCat(rs.getString("subCat"));
                results.add(category);
            }
        } while (rs.next());

        return results;
    }

}
