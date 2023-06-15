package com.example.todoapp.model.user;

import com.example.todoapp.model.DataModel;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Address extends DataModel {
    String id;
    String name;
    String phone;
    String pinCode;
    String state;
    String city;
    String houseNo;
    String locality;
    String type;
}
