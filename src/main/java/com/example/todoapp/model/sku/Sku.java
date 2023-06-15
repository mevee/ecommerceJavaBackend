package com.example.todoapp.model.sku;

import com.example.todoapp.model.DataModel;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Sku extends DataModel {
    int id;
    String name;
    String description;
    String liquidIndicator;
    String state;
    int unit;
    int weight;
    String highValue;
}