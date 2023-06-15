package com.example.todoapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;


public class ApiResponse implements Serializable {
    @JsonInclude(Include.NON_NULL)
    private String errorCode;
    @JsonInclude(Include.NON_NULL)
    private String messageText;

    public ApiResponse() {
        super();
    }

    public ApiResponse(String errorCode, String messageText) {
        super();
        this.errorCode = errorCode;
        this.messageText = messageText;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    @Override
    public String toString() {
        return "ApiResponse{" + "errorCode=" + errorCode + ", messageText=" + messageText + '}';
    }

}
