package com.example.todoapp.model;

import lombok.ToString;

import java.util.List;
@ToString
public class GenericResponse<T> extends ApiResponse {
    private T data;
    private Meta meta;
    private List<Meta> errors;
    public GenericResponse(){
        this.meta = new Meta();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Meta> getErrors() {
        return errors;
    }

    public void setErrors(List<Meta> errors) {
        this.errors = errors;
    }
}
