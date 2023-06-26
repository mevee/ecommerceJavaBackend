package com.ecommerce.util;

import com.ecommerce.model.Meta;
import com.ecommerce.model.GenericResponse;

public class CommonUtils {
    static public <T> GenericResponse<T> getSuccessResponse(T data) {
        GenericResponse response = new GenericResponse();
        System.out.println("---------------getSuccessResponse()---------------");
        response.getMeta().setMessageCode(AppConstants.SUCCESS_CODE);
        response.getMeta().setMessageDescription(AppConstants.SUCCESS_MSG);
        response.getMeta().setStatus(AppConstants.SUCCESS_MSG);
        response.getMeta().setRequestId("" + System.currentTimeMillis());
        response.setData(data);
        return response;
    }

    static public <T> GenericResponse<T> paramMissing(GenericResponse<T> response) {
        Meta meta = response.getMeta();
        meta.setMessageCode(AppConstants.ERR400);
        meta.setMessageDescription(AppConstants.BAD_CREDENTIALS);
        meta.setStatus(AppConstants.ERR400);
        meta.setRequestId("" + System.currentTimeMillis());
        return response;
    }
    static public <T> GenericResponse<T> error(GenericResponse<T> response,String message) {
        Meta meta = response.getMeta();
        meta.setMessageCode(AppConstants.ERR400);
        meta.setMessageDescription(message);
        meta.setStatus(AppConstants.ERR400);
        meta.setRequestId("" + System.currentTimeMillis());
        return response;
    }

}
