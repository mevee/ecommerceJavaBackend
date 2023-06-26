package com.ecommerce.rowmapper;

import com.ecommerce.model.sku.ProductImage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductImagesMapper implements RowMapper<List<ProductImage>> {
    @Override
    public List<ProductImage> mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<ProductImage> list = new ArrayList<>();
        do {
            if (rs!=null){
                ProductImage image = new ProductImage();
                image.setId(""+rs.getInt("id"));
                image.setPId(""+rs.getInt("pId"));
                image.setImage(rs.getString("image"));
                list.add(image);
            }
        }while (rs.next());

        return list;
    }
}
