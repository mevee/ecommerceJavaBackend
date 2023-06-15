package com.example.todoapp.util;

import com.example.todoapp.model.GenericResponse;
import com.example.todoapp.model.Meta;

import static com.example.todoapp.util.AppConstants.*;

public class CommonUtils {
    static public <T> GenericResponse<T> getSuccessResponse(T data) {
        GenericResponse response = new GenericResponse();
        System.out.println("---------------getSuccessResponse()---------------");
        response.getMeta().setMessageCode(SUCCESS_CODE);
        response.getMeta().setMessageDescription(SUCCESS_MSG);
        response.getMeta().setStatus(SUCCESS_MSG);
        response.getMeta().setRequestId("" + System.currentTimeMillis());
        response.setData(data);
        return response;
    }

    static public <T> GenericResponse<T> paramMissing(GenericResponse<T> response) {
        Meta meta = response.getMeta();
        meta.setMessageCode(ERR400);
        meta.setMessageDescription(BAD_CREDENTIALS);
        meta.setStatus(ERR400);
        meta.setRequestId("" + System.currentTimeMillis());

        return response;
    }

}
