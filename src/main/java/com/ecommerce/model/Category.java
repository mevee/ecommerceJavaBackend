package com.ecommerce.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Category extends DataModel{
    String id;
    String name;
    String description;
    String subCat;
    String image;
}
