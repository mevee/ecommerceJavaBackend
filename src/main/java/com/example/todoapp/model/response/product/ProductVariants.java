package com.example.todoapp.model.response.product;

import com.example.todoapp.model.ApiResponse;
import com.example.todoapp.model.DataModel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ToString
public class ProductVariants extends DataModel {
    int id;
    String name;
    String description;
    String color;
    String size;
    String sku;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
